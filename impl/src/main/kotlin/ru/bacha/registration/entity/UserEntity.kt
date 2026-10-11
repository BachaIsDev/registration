package ru.bacha.registration.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
class UserEntity (
    @Id
    val uuid : UUID,
    var email : String,
    var password : String,
) {
}