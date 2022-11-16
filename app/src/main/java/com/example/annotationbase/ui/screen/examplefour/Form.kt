package com.example.annotationbase.ui.screen.examplefour

import android.util.Patterns
import android.util.Range
import com.example.annotationbase.ui.screen.examplefour.annotations.Email
import com.example.annotationbase.ui.screen.examplefour.annotations.FormEntity
import com.example.annotationbase.ui.screen.examplefour.annotations.Password
import java.lang.reflect.Field
import java.util.regex.Pattern

abstract class Form {

    val ONE_UPPER_LOWER_DIGIT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$"

    var _errors = ArrayList<FormFieldError>()
    val errors: List<FormFieldError>
        get() = _errors

    fun validate(): Boolean {
        _errors = ArrayList()
        javaClass.getAnnotation(FormEntity::class.java)?.let {
            validateFields(this)
        }
        return _errors.size <= 0
    }


    protected open fun validateFields(form: Any) {
        for (field in form.javaClass.declaredFields) {
            field.isAccessible = true
            for (annotation in field.annotations) {
                when (annotation) {
                    is Email -> validateEmail(form, annotation, field)
                }
            }
        }
    }


    protected open fun validateEmail(form: Any, annotation: Email, field: Field) {
        val email = field[form] as String?
        when {
            email.isNullOrEmpty() -> {
                var errorMessage = "Enter your email address"
                if (annotation.emptyFieldMessage.isNotEmpty()) {
                    errorMessage = annotation.emptyFieldMessage
                }
                _errors.add(
                    FormFieldError(
                        errorCode = annotation.errorCode,
                        message = errorMessage
                    )
                )
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                _errors.add(
                    FormFieldError(
                        errorCode = annotation.errorCode,
                        message = "Enter a valid email address"
                    )
                )
            }
        }
    }


    protected open fun validatePassword(form: Any, annotation: Password, field: Field) {

        val password = field[form] as String?
        when (annotation.type) {
            Password.Type.LOGIN -> {
                if (password.isNullOrEmpty()) {
                    var errorMessage = "Enter your ${annotation.name}"
                    if (annotation.emptyFieldMessage.isNotEmpty()) {
                        errorMessage = annotation.emptyFieldMessage
                    }
                    _errors.add(
                        FormFieldError(
                            errorCode = annotation.errorCode,
                            message = errorMessage
                        )
                    )
                }
            }
            Password.Type.SIGN_UP -> {
                val regex: Pattern =
                    Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]")
                when {
                    password.isNullOrEmpty() -> {
                        var errorMessage = "Enter your ${annotation.name}"
                        if (annotation.emptyFieldMessage.isNotEmpty()) {
                            errorMessage = annotation.emptyFieldMessage
                        }
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = errorMessage
                            )
                        )
                    }
                    !Pattern.matches(ONE_UPPER_LOWER_DIGIT, password) -> {
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = "Password must contain 8 characters. Include at least one upper case(A-Z), lower case(a-z), one special character and one number(0-9)"
                            )
                        )
                    }
                    !regex.matcher(password).find() -> {
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = "Password must contain 8 characters. Include at least one upper case(A-Z), lower case(a-z), one special character and one number(0-9)"
                            )
                        )
                    }
                    password.length !in
                            Range(annotation.minLength, annotation.maxLength) -> {
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = "Password length must be in between " +
                                        "${annotation.minLength} - ${annotation.maxLength}"
                            )
                        )
                    }
                }
            }
            Password.Type.CONFIRM -> {
                var passwordSignUp: String? = null
                form.javaClass.declaredFields.forEach { theField ->
                    theField.isAccessible = true
                    theField.getAnnotation(Password::class.java)?.let { password ->
                        if (password.type == Password.Type.SIGN_UP) {
                            passwordSignUp = theField[form] as String?
                        }
                    }
                }
                val confirmPassword = field[form] as String?
                when {
                    confirmPassword.isNullOrEmpty() -> {
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = "Enter password again"
                            )
                        )
                    }
                    passwordSignUp != confirmPassword -> {
                        _errors.add(
                            FormFieldError(
                                errorCode = annotation.errorCode,
                                message = "Password did not match"
                            )
                        )
                    }
                }
            }
        }
    }

}