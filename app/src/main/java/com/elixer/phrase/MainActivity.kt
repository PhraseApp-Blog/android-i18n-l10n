package com.elixer.phrase

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val UtcTimeStamp = "2022-01-31T16:27:23Z"
    val timestampInstant = Instant.parse(UtcTimeStamp)
    val zonedDateTime = ZonedDateTime.ofInstant(timestampInstant, ZoneId.systemDefault())
    val dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    val localizedDate = zonedDateTime.format(dateFormatter)

    setContent {
      PhraseTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {

          Column(
            Modifier
              .fillMaxSize()
              .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {

            Text(
              text = "UTC TimeStamp: $UtcTimeStamp",
              color = Color.Blue,
              modifier = Modifier.padding(top = 30.dp),
              fontSize = 20.sp
            )
            Text(
              text = localizedDate.toString(),
              fontSize = 20.sp,
              modifier = Modifier.padding(top = 30.dp)
            )
          }
        }
      }
    }
  }
}