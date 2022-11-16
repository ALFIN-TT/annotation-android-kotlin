package com.example.annotationbase.ui.screen.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//https://proandroiddev.com/the-guide-to-your-first-annotation-processor-with-ksp-and-becoming-a-kotlin-artist-4e5d13f171e6
//https://beltran.work/blog/hello-world-of-annotation-processing-in-kotlin/
@Composable
fun IntroScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 50.dp)
    ) {

        Text(
            text = "Kotlin Annotations",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Annotations are used at compile time to attach metadata to classes, interfaces, parameters, etc. The compiler can apply annotations, which reflect at runtime. According to the annotation values, we can change the meaning of the data or program.",
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )
    }
}