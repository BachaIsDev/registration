package ru.bacha.registration.dto

data class JwtAuthenticationDto (
    val token: String,
    val refreshToken: String,
) {

}
