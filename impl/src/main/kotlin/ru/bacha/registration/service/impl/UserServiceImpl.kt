package ru.bacha.registration.service.impl

import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.bacha.model.UserDto
import ru.bacha.registration.dto.JwtAuthenticationDto
import ru.bacha.registration.dto.RefreshTokenDto
import ru.bacha.registration.dto.UserCredentialsDto
import ru.bacha.registration.entity.UserEntity
import ru.bacha.registration.mapper.UserMapper
import ru.bacha.registration.repository.UserRepository
import ru.bacha.registration.security.JwtService
import ru.bacha.registration.service.UserService
import java.util.UUID
import javax.naming.AuthenticationException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
) : UserService {
    companion object {
        val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

    override fun signIn(credentialsDto: UserCredentialsDto): JwtAuthenticationDto {
        val user = findByCredentials(credentialsDto)
        return jwtService.generateAuthToken(user.email)
    }

    override fun refreshToken(token: RefreshTokenDto): JwtAuthenticationDto {
        if (jwtService.validateJwtToken(token.refreshToken)) {
            val user = findByEmail(jwtService.getEmailFromToken(token.refreshToken))
            return jwtService.refreshBaseToken(user.email, token.refreshToken)
        }

        throw AuthenticationException("Invalid refresh token")
    }

    override fun getUserById(userId: UUID): UserDto {
        return userMapper.toDto(
            userRepository.findById(userId)
                .orElseThrow { EntityNotFoundException("Пользователь с id: $userId не найден") }
        )
    }

    override fun getUserByEmail(username: String): UserDto {
        return userMapper.toDto(
            userRepository.findByEmail(username)
                .orElseThrow { EntityNotFoundException("Пользователь с email: $username не найден") }
        )
    }

    override fun addUser(userDto: UserDto): UserDto {
        val toSave = userMapper.toEntity(userDto)
        toSave.password = passwordEncoder.encode(userDto.password)
        return userMapper.toDto(userRepository.save(toSave))
    }

    private fun findByCredentials(credentials: UserCredentialsDto): UserEntity {
        val user = findByEmail(credentials.email)
        if (passwordEncoder.matches(credentials.password, user.password)) {
            return user
        }

        throw AuthenticationException("Email or password incorrect")
    }

    private fun findByEmail(name: String): UserEntity {
        return userRepository.findByEmail(name).orElseThrow { EntityNotFoundException() }
    }

}