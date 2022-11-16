package com.example.annotation

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class CheckCamelSource


//CASE 03 STEP 01 -create kotlin module 'annotation' and create annotation
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
annotation class GenerateSource(val value: Int)


@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class Encapsulate



