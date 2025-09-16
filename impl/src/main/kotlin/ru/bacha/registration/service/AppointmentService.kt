package ru.bacha.registration.service

import ru.bacha.model.AppointmentDto
import ru.bacha.registration.persistence.model.Appointment

interface AppointmentService {
    fun createAppointment(dto: AppointmentDto): Appointment
}