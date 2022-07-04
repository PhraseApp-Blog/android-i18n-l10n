package com.elixer.phrase

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elixer.phrase.ui.theme.PhraseTheme
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val localeManager = getSystemService(Context.LOCALE_SERVICE) as LocaleManager
    val currentLanguage = localeManager.applicationLocales.toLanguageTags()

    setContent {
      PhraseTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          val supportedLocales = listOf("en", "es", "ja")
          var expanded by remember { mutableStateOf(false) }
          var selectedLocale by remember {
            mutableStateOf(supportedLocales.find { it == currentLanguage } ?: "Not Set")
          }

          Column(
            Modifier
              .fillMaxSize()
              .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {

            Text(
              text = "Current Locale: ${
                if (currentLanguage.isNullOrEmpty()) "Not Set" else currentLanguage
              }",
              color = Color.Blue,
              modifier = Modifier.padding(top = 30.dp)
            )

            ExposedDropdownMenuBox(
              expanded = expanded,
              onExpandedChange = {
                expanded = !expanded
              }
            ) {
              TextField(
                readOnly = true,
                value = selectedLocale,
                onValueChange = { },
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
                supportedLocales.forEach { selectionOption ->
                  DropdownMenuItem(onClick = {
                    selectedLocale = selectionOption
                    localeManager.applicationLocales =
                      LocaleList(Locale.forLanguageTag(selectedLocale))
                    expanded = false
                  }
                  ) {
                    Text(text = selectionOption)
                  }
                }
              }
            }
            Text(
              // getString is a function that will return the
              // translated String using the "greeting" key
              text = getString(R.string.greeting),
              fontSize = 60.sp,
              modifier = Modifier.padding(top = 30.dp)
            )
            Button(onClick = {
              localeManager.applicationLocales = LocaleList.getEmptyLocaleList()
            }, modifier = Modifier.padding(top = 200.dp)) {
              Text(text = "Reset")
            }
          }
        }
      }
    }
  }
}