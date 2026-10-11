package ru.bacha.registration.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import ru.bacha.model.UserDto
import ru.bacha.registration.entity.UserEntity

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDto(user: UserEntity): UserDto

    @Mapping(target = "uuid", expression = "java(com.github.f4b6a3.uuid.UuidCreator.getTimeOrderedEpoch())")
    fun toEntity(user: UserDto): UserEntity
}