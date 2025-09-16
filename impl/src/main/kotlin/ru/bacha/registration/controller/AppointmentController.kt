package ru.bacha.registration.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ru.bacha.api.AppointmentApi
import ru.bacha.model.AppointmentDto
import ru.bacha.registration.service.AppointmentService

@RestController
@RequiredArgsConstructor
class AppointmentController(
        val service: AppointmentService
) : AppointmentApi {
    override fun createAppointment(appointmentDto: AppointmentDto): ResponseEntity<Unit> {
        service.createAppointment(appointmentDto);
        return ResponseEntity.ok().build()
    }
}