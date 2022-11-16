package com.example.annotationbase.ui.screen.examplefour.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Email(
    val name: String = "email",
    val emptyFieldMessage: String = "",
    val errorCode: Int
)
