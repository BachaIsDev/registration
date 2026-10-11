package ru.bacha.registration.controller.advice

import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(AuthenticationException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleException(e: AuthenticationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed: ${e.localizedMessage}")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: ${e.localizedMessage}")
    }
}