package com.example.annotationbase.ui.screen.examplefour.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Password(
    val name: String = "password",
    val emptyFieldMessage: String = "",//error message to shows when name field is empty.
    val type: Type = Type.LOGIN,
    val minLength: Int = 0,
    val maxLength: Int = 0,
    val errorCode: Int
){
    enum class Type{
        LOGIN ,
        SIGN_UP,
        CONFIRM
    }
}