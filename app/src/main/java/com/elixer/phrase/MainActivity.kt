package com.elixer.phrase

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elixer.phrase.ui.theme.PhraseTheme

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
          Column(Modifier.padding(top = 200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = getString(R.string.landing_url),
              fontSize = 20.sp,
              modifier = Modifier.padding(start = 10.dp)
            )
            Button(onClick = {}) {
              Text(text = getString(R.string.next))
            }
            Button(onClick = {}) {
              Text(text = "Back")
            }
          }
        }
      }
    }
  }
}