package com.example.annotationbase.ui.screen.examplefour

import com.example.annotationbase.ui.screen.examplefour.annotations.Email
import com.example.annotationbase.ui.screen.examplefour.annotations.FormEntity
import com.example.annotationbase.ui.screen.examplefour.annotations.Password

const val ERROR_CODE_EMAIL = 100
const val ERROR_CODE_PASSWORD = 101

@FormEntity()
class LoginForm(
    @Email(
        name = "email",
        emptyFieldMessage = "Enter your email",
        errorCode = ERROR_CODE_EMAIL
    )
    var email: String? = null,

    @Password(
        name = "password",
        emptyFieldMessage = "Enter your password",
        errorCode = ERROR_CODE_PASSWORD
    )
    var password: String? = null,
) : Form() 