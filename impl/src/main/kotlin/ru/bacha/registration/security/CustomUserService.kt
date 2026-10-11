package ru.bacha.registration.security

import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.bacha.registration.entity.UserEntity
import ru.bacha.registration.repository.UserRepository

@Service
class CustomUserService(
    val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return CustomUserDetails(getByUsername(username.orEmpty()))
    }

    private fun getByUsername(email: String): UserEntity {
        return userRepository.findByEmail(email).orElseThrow { EntityNotFoundException("User with email: $email not found") }
    }
}
