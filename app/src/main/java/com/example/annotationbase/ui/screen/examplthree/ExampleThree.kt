package com.example.annotationbase.ui.screen.examplthree

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.annotation.CheckCamelSource
import com.example.annotation.GenerateSource
import com.example.annotationbase.MainActivity

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExampleThree(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 22.dp)
    ) {

        Text(
            text = "Types of Annotation processing",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Annotation Processing are three types.\n" +
                    "1.Using reflection for processing\n" +
                    "2.Using AbstractProcessor for preprocessing\n" +
                    "3.Using kotlinpoet for generating new codes",
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "note: Please check check source code for understand Annotations",
            style = TextStyle(fontWeight = FontWeight.Light)
        )

        // There are 3 types of RetentionPolicy s
        // 1.SOURCE — A tag that stays with the programming element before it is compiled. It get stripped away during compilation
        // 2.BINARY — A tag that stays forever with the programming element even after compiled. But it is not visible via reflection.
        // 3.RUNTIME — A tag that stays forever with the programming element even after compiled, and it is visible via reflection.


        // Tags are sometimes specific to some elements. So to define what it applies to, in Kotlin, we use AnnotationTarget type to define it. So you could decide if the tag could be used in your Class, Function, Variable etc...


        // Annotation Processing are three types.
        // 1.Using reflection for processing
        // 2.Using AbstractProcessor for preprocessing
        // 3.Using kotlinpoet for generating new codes

        //1.Using Reflection for Processing
        data class Test(
            @ReflectRuntime(value = 5)
            var reflectTest: Int = 0
        )

        val test = Test()
        println(test)
        println(bindReflectionValue(test))
        // This function will use reflection to list through all variables declared within the class, and find any that is annotated with ReflectRuntime. Then it makes the variable writeable and set it per what the annotation value is i.e. 5.


        //2.Using AbstractProcessor for Preprocessing
        // In this project have 2 modules (kotlin module)  1. annotation module, 2. processor module

        //In the app module, you just need to link to the annotation and processor, using the below.

        //TODO add this commented code in your app modules 'build.gradle' file
        /*compileOnly project(path: ':annotation')
         kapt project(':processor')*/

        //TODO add  this line also- id 'kotlin-kapt'
        /*plugins {
             id 'com.android.application'
             id 'org.jetbrains.kotlin.android'
             id 'kotlin-kapt' //this line
         }*/

        //TODO please refer diagram from path - 'assets/abstract_processor_diagram.png' for better understanding.

        // Note that for the annotation, it is only compileOnly, and the processor is using the kapt.

        // Using the annotation
        // For the class below, it will report a warning for TAG as it doesn’t conform to CamelCase

        //TODO uncomment code and run check Build tab to see warning.
        /*@CheckCamelSource
         class MainActivity : AppCompatActivity() {
             companion object {
                 const val TAG = "AnnotateTest"
             }
         }*/


        //3.Using kotlinpoet for Generating New Codes (CASE 03 STEP 00)

        // The AbstractProcessor by itself is only handy in perform validation. It is not that useful, if it can’t do something that would be used at runtime.
        // The Lib 'KotlinPoet' enable it to generate source codes
        // Combine KotlinPoet with AbstractProcessor processing, we could extract the annotated value, and generate the needed source code.

        //Writing a function to a file

        // please check annotation module and processor module and follow steps

        // TODO follow this steps
        //CASE 03 STEP 01 - create kotlin module 'annotation' and create annotation
        //CASE 03 STEP 02 - create kotlin module 'processor' and add dependencies. check 'processor' s  'build.gradle' file
        //CASE 03 STEP 03 - import added modules to apps 'build.gradle' file

        // The file it generate looks like below

        //TODO please refer MainActivity and find following code snippets
        /*@GenerateSource(6)
         var integerValue: Int = 0*/
        // this snippet for generate method for set value 6 for annotated variable

        /*bindGenerationValue(this)*/
        //this is the method generated after the annotation parsing. we can call this auto generated method from our activity.

        //TODO find this class from generated packages after add annotated variable and rebuild.
        /*package com.example.annotationbase

         fun bindGenerationValue(parent: MainActivity) {
             parent.integerValue = 6
         }*/

    }
}
