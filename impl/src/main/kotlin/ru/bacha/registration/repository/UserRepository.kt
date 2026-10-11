package ru.bacha.registration.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.bacha.registration.entity.UserEntity
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): Optional<UserEntity>

    override fun findById(id: UUID): Optional<UserEntity>
}