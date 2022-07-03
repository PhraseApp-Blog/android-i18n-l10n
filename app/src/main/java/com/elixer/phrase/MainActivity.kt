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
    // A surface container using the 'background' color from the theme
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
       // getString is a function that will return the
       // translated String using the "greeting" key
       text = getString(R.string.greeting), fontSize = 60.sp
      )
     }
    }
   }
  }
 }
}