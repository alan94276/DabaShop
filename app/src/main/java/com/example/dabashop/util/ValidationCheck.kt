package com.example.dabashop.util

import android.util.Patterns

fun validateEmail(email : String) : RegisterValidation{
    if (email.isEmpty())
        return RegisterValidation.Failed("El correo no puede estár vacío.")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return  RegisterValidation.Failed("El formato de correo no es el correcto.")

    return RegisterValidation.Success
}

fun validatePassword(password: String) : RegisterValidation{
    if (password.isEmpty())
        return RegisterValidation.Failed("La contraseña no puede estár vacía")
    if (password.length < 6)
        return RegisterValidation.Failed("La contraseña debe contee más de 6 caracteres.")

    return  RegisterValidation.Success

}