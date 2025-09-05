package ru.bacha.registration.service

import ru.bacha.registration.persistence.model.Appointment

interface AppointmentService {
    fun createAppointment(appointment: Appointment): Appointment
}