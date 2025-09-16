package ru.bacha.registration.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.bacha.api.AppointmentApi
import ru.bacha.model.AppointmentDto
import ru.bacha.registration.persistence.model.Appointment

@RestController
@RequiredArgsConstructor
class AppointmentController : AppointmentApi {
    override fun createAppointment(appointmentDto: AppointmentDto?): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }
}