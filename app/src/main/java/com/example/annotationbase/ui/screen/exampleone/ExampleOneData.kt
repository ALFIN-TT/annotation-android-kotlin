package com.example.annotationbase.ui.screen.exampleone

import androidx.annotation.IntDef
import androidx.annotation.StringDef

// Declare the constants
const val DISPLAY_USE_LOGO = 0
const val DISPLAY_SHOW_HOME = 1
const val DISPLAY_HOME_AS_UP = 2
const val DISPLAY_SHOW_TITLE = 3
const val DISPLAY_SHOW_CUSTOM = 4

/***
 * When you build code with an annotation flag, a warning is generated if the decorated parameter or return value does not reference a valid pattern.
 *
 * @param flag  a flag attribute to check if a parameter or return value references a valid pattern.
 */
@IntDef(
    flag = true, value = [
        DISPLAY_USE_LOGO,
        DISPLAY_SHOW_HOME,
        DISPLAY_HOME_AS_UP,
        DISPLAY_SHOW_TITLE,
        DISPLAY_SHOW_CUSTOM,
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class DisplayOptions


class TestDisplayOptions(@DisplayOptions val displayOptions: Int, val text: String) {

    fun performTaskOne() {
        println("Text: $text")
        println("Display Options: $displayOptions")
    }
}

const val PURPLE = "#BB86FC"
const val TEAL = "#03DAC5"
const val BLACK = "#000000"
const val WHITE = "#ffffff"
const val BLUE = "#3700B3"

@StringDef(
    value = [
        PURPLE,
        TEAL,
        BLACK,
        WHITE,
        BLUE
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class ColorOptions


class TestColorOptions(@ColorOptions val colorOptions: String, val text: String) {

    fun performTaskOne() {
        println("Text: $text")
        println("Color Options: $colorOptions")
    }
}