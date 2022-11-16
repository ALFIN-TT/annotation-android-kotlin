package com.example.annotationbase.ui.screen.examplefour.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
annotation class FormEntity(val name: String = "form")