package com.example.annotationbase.ui.screen.examplefive

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.lang.String

@Composable
fun ExampleFive(navController: NavController) {

    // TODO Check this modules
    // app — our application
    // annotations — annotation module
    // processor — annotation processor module


    //TODO check com.example.annotationbase.ui.screen.examplefive.ExampleFiveData file there are two classes annotated with '@Encapsulate'

    // accessing generated class
    val exampleModel = EncapsulatedExampleFive()
    exampleModel.getname()
    exampleModel.setname("Minnu" as String)

    println("name: ${exampleModel.getname()}")

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Creating getter and setter of given class using annotation 'kotlin poet'",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "note: Please check source code for understand Annotations",
            style = TextStyle(fontWeight = FontWeight.Light)
        )
    }
}