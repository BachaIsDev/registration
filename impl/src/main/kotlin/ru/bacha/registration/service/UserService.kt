package ru.bacha.registration.service

import ru.bacha.model.UserDto
import ru.bacha.registration.dto.JwtAuthenticationDto
import ru.bacha.registration.dto.RefreshTokenDto
import ru.bacha.registration.dto.UserCredentialsDto

import java.util.UUID

interface UserService {

    fun signIn(credentialsDto: UserCredentialsDto): JwtAuthenticationDto
    fun refreshToken(token: RefreshTokenDto): JwtAuthenticationDto
    fun getUserById(userId: UUID): UserDto
    fun getUserByEmail(username: String): UserDto
    fun addUser(userDto: UserDto): UserDto
}