package ru.bacha.registration.validator

fun interface ValidationService<T> {
    fun validate(validated: T)
}