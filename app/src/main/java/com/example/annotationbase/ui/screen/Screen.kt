package com.example.annotationbase.ui.screen

sealed class Screen(val route: String) {

    object MainScreen : Screen("main_screen")
    object IntroScreen : Screen("intro_screen")
    object ExampleOne : Screen("example_one")
    object ExampleTwo : Screen("example_two")
    object ExampleThree : Screen("example_three")
    object ExampleFour : Screen("example_four")
    object ExampleFive : Screen("example_five")

}