package com.example.dabashop.util

sealed class RegisterValidation(){
    object Success: RegisterValidation()
    data class Failed(val message: String): RegisterValidation()
}

data class RegisterFieldsSate(
    val email : RegisterValidation,
    val password : RegisterValidation
)
