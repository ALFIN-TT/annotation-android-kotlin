package com.example.annotationbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.annotation.CheckCamelSource
import com.example.annotation.GenerateSource
import com.example.annotationbase.ui.screen.Navigation
import com.example.annotationbase.ui.theme.AnnotationBaseTheme


@CheckCamelSource //used for annotation parsing
class MainActivity : ComponentActivity() {

    companion object {
        //used for annotation parsing warning.
        const val TAG = "AnnotateTest"
    }


    //CASE 02 STEP 01
    //kotlin poet annotation parsing.
    @GenerateSource(6)
    var integerValue: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        //calling auto generated function
        //bindGenerationValue(this)

        setContent {
            AnnotationBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}