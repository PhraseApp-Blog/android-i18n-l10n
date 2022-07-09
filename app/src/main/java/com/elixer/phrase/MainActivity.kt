package com.elixer.phrase

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elixer.phrase.ui.theme.PhraseTheme
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
  @RequiresApi(Build.VERSION_CODES.TIRAMISU)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)



    setContent {
      PhraseTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {

          var count by remember { mutableStateOf("0") }
          val resources = LocalContext.current.resources

          Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
              top =
              20.dp
            )
          ) {
            Spacer(modifier = Modifier.weight(1f))
            TextField(
              modifier = Modifier.width(80.dp),
              value = count, onValueChange = { count = it.ifEmpty { "0" } },
              keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
            Text(
              text =  resources.getQuantityString(R.plurals.label_minutes, count.toInt()),
              fontSize = 20.sp,
              modifier = Modifier.padding(start = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
          }
        }
      }
    }
  }
}