package ru.bacha.registration.persistence.repository

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.bacha.registration.persistence.model.Appointment

@Repository
interface AppointmentRepository: JpaRepository<Appointment, UUID> {
}