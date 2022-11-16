package com.example.annotationbase.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.annotationbase.ui.screen.Screen

@Composable
fun MainScreen(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 50.dp)
    ) {

        Button(
            onClick = {
                navController.navigate(Screen.IntroScreen.route)
            }
        ) {
            Text(text = "Introduction")
        }

        Button(
            onClick = {
                navController.navigate(Screen.ExampleOne.route)
            }
        ) {
            Text(text = "Example One")
        }

        Button(
            onClick = {
                navController.navigate(Screen.ExampleTwo.route)
            }
        ) {
            Text(text = "Example Two")
        }


        Button(
            onClick = {
                navController.navigate(Screen.ExampleThree.route)
            }
        ) {
            Text(text = "Example Three")
        }


        Button(
            onClick = {
                navController.navigate(Screen.ExampleFour.route)
            }
        ) {
            Text(
                text = "Example Four: Form validation using annotation",
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                navController.navigate(Screen.ExampleFive.route)
            }
        ) {
            Text(text = "Example Five")
        }

        Button(
            onClick = {
                navController.navigate(Screen.ExampleFive.route)
            }
        ) {
            Text(text = "Example Four")
        }

    }
}