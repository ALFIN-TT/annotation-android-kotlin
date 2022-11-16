package com.example.annotationbase.ui.screen.exampltwo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExampleTwo(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {

        Text(text = "This annotation could be used if you are writing your framework and want to suggest users call the method from a background thread instead of a UI thread")
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Please check logcat Debug in waring message like: D/WARNING: operation1 should be called from a background thread.",
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "note: Please check check source code for understand Annotations",
            style = TextStyle(fontWeight = FontWeight.Light)
        )


        val test = Test()
        test.operationOne()// Will generate Warning.

        val coroutineScope = rememberCoroutineScope()

        coroutineScope.launch(Dispatchers.IO) {
            test.operationOne() // called rightly, no warning
        }
    }
}