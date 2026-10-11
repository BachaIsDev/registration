package ru.bacha.registration.controller

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.bacha.registration.dto.JwtAuthenticationDto
import ru.bacha.registration.dto.RefreshTokenDto
import ru.bacha.registration.dto.UserCredentialsDto
import ru.bacha.registration.security.JwtService
import ru.bacha.registration.service.UserService

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(JwtService::class.java)
    }

    @PostMapping("/sign-in")
    fun singIn(@RequestBody userCredentialsDto: UserCredentialsDto): ResponseEntity<JwtAuthenticationDto> {
        val authentication = userService.signIn(userCredentialsDto)
        logger.info("жвт и рефреш: $authentication")
        return ResponseEntity.ok(authentication)
    }

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody refreshToken: RefreshTokenDto): JwtAuthenticationDto {
        return userService.refreshToken(refreshToken)
    }
}