package ru.bacha.registration.persistence.model

import java.time.LocalDateTime
import java.util.UUID

data class Appointment(
    val id: UUID,
    var appointmentDate: LocalDateTime,
    var doctorId: UUID,
    var type: AppointmentType,
)
