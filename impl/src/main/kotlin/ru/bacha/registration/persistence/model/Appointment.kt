package ru.bacha.registration.persistence.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "appointment")
data class Appointment(
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    val id: UUID,
    var appointmentDate: LocalDateTime,
    var doctorId: UUID,
    var type: AppointmentType,
    var additionalInfo: String?,
)
