package com.elixer.phrase

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.elixer.phrase.ui.theme.PhraseTheme
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get user's preferred Locales
        val locale = LocaleList.getDefault()
        val current = resources.configuration.locale

        val adjustedDefault: LocaleListCompat = LocaleListCompat.getDefault()
        val options = listOf("en_rIN", "fr", "ja")

        //This returns a list of Locales
        setContent {
            PhraseTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(getString(R.string.greeting), fontSize = 30.sp)
                        Text("Android")
                        Text("Locale:  $locale", fontSize = 22.sp)
                        Text("adjustedDefault:  $adjustedDefault", fontSize = 22.sp)
                        Text("Locale Tags:    ${locale.toLanguageTags()}", fontSize = 22.sp)

                        var expanded by remember { mutableStateOf(false) }
                        var selectedOptionText by remember { mutableStateOf(options[0]) }

                        ExposedDropdownMenuBox(
                            modifier = Modifier.padding(top = 100.dp),
                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }
                        ) {
                            TextField(
                                readOnly = true,
                                value = selectedOptionText,
                                onValueChange = { selectedLocale ->
//                                    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(selectedLocale)
//                                    AppCompatDelegate.setApplicationLocales(appLocale)

                                },
                                label = { Text("Label") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                colors = ExposedDropdownMenuDefaults.textFieldColors()
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                }
                            ) {
                                options.forEach { selectedLocale ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedOptionText = selectedLocale
                                            expanded = false
                                            val locales = Locale.forLanguageTag(selectedLocale)
                                            val localeManager =  getSystemService(LocaleManager::class.java)
                                            localeManager.applicationLocales = LocaleList(locales)
                                        }
                                    ) {
                                        Text(text = selectedLocale)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhraseTheme {
        Greeting("Android")
    }
}