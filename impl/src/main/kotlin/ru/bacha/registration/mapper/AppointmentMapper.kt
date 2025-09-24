package ru.bacha.registration.mapper

import org.mapstruct.Mapper
import ru.bacha.model.AppointmentDto
import ru.bacha.registration.persistence.model.Appointment

@Mapper(componentModel = "spring")
interface AppointmentMapper {
    fun map(toMap: AppointmentDto): Appointment
}