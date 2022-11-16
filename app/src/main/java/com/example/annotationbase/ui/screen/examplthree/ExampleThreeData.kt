package com.example.annotationbase.ui.screen.examplthree


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.LOCAL_VARIABLE)
annotation class ReflectRuntime(val value: Int)


fun bindReflectionValue(target: Any): Any {
    val declaredFields = target::class.java.declaredFields
    for (field in declaredFields) {
        for (annotation in field.annotations) {
            return when (annotation) {
                is ReflectRuntime -> {
                    field.isAccessible = true
                    field.set(target, annotation.value)
                    target
                }
                else -> target
            }
        }
    }
    return target
}
