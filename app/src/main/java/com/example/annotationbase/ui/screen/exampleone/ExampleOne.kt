package com.example.annotationbase.ui.screen.exampleone

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ExampleOne(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {
        Text(
            text = "Enable combining constants with flags",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Usage of '@StringDef' and '@IntDef",
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "note: Please check check source code for understand Annotations",
            style = TextStyle(fontWeight = FontWeight.Light)
        )

        val displayOptions1 =
            TestDisplayOptions(1, "direct specifying display option")// shows compiler error

        val displayOptions2 =
            TestDisplayOptions(
                DISPLAY_USE_LOGO,
                "specifying display option from annotation class"
            )// OK

        val colorOptions1 =
            TestColorOptions("#fffff", "direct specifying color option") // shows compiler error

        val colorOptions2 =
            TestColorOptions(WHITE, "specifying color option from annotation class")// OK
    }
}