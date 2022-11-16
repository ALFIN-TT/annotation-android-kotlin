package com.example.annotationbase.ui.screen.exampltwo

import android.os.Looper
import android.util.Log
import java.lang.reflect.Method

/**
 *  The Target tells which entity this annotation applies to, in this case, it is a Function. The other values could be CLASS, PROPERTY, CONSTRUCTOR, LOCAL_VARIABLE, etc.
 *
 *  The Retention determines how annotation is stored in binary output. The other values are SOURCE(Annotation isn’t stored in binary output), BINARY (Annotation is stored in binary output but not available for reflection). RUNTIME (default)tells the annotation to be stored in binary output and visible to reflection
 *
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class BackgroundThread

/***
 * The class has a class method that just checks if the method is annotated with the “@BackgroundThread” and if it does it checks if it is being called from the UI thread. If Yes then generates the warning.
 */
class AnnotationUtility {
    companion object {
        fun checkThreadWarnings(method: Method?) {
            if (method != null) {
                try {
                    val backgroundThreadAnnotation = BackgroundThread::class.java
                    val annotation = method.getAnnotation(backgroundThreadAnnotation)
                    if (annotation != null && Looper.myLooper() == Looper.getMainLooper()) {
                        Log.d(
                            "WARNING",
                            method.name + " should be called from a background thread."
                        )
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.toString())
                }
            }
        }
    }
}


/***
 * Defines a method annotates it with “@BkThread”. The method gives a call to the AnnotationUtility method to verify the thread. Giving a call to checkThreadWarning(method) manually may look an overhead but this worth it to ensure the programmers comply with the rule, especially if you are creating a framework or library.
 */
class Test {

    @BackgroundThread
    fun operationOne() {
        AnnotationUtility.checkThreadWarnings(object {}.javaClass.enclosingMethod)
        //do your work here
    }
}