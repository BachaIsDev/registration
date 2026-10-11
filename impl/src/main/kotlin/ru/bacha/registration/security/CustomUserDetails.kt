package ru.bacha.registration.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.bacha.registration.entity.UserEntity

class CustomUserDetails (
    val user: UserEntity,
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return listOf()
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
       return user.email
    }
}