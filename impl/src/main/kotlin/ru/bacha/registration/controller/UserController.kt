package ru.bacha.registration.controller

import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.bacha.model.UserDto
import ru.bacha.registration.service.UserService
import java.util.UUID


@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping("/registration")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.addUser(userDto)
        return ResponseEntity.ok("User created successfully")
    }

    @GetMapping("/{id}")
    @Throws(ChangeSetPersister.NotFoundException::class)
    fun getUserById(@PathVariable id: String): UserDto {
        return userService.getUserById(UUID.fromString(id))
    }

    @GetMapping("/email/{email}")
    @Throws(ChangeSetPersister.NotFoundException::class)
    fun getUserByEmail(@PathVariable email: String): UserDto {
        return userService.getUserByEmail(email)
    }
}

