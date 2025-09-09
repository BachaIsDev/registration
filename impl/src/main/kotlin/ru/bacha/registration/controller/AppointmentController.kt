package ru.bacha.registration.controller

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RestController
import ru.bacha.registration.persistence.model.Appointment

@RestController
@RequiredArgsConstructor
class AppointmentController {
    fun createAppointment(appointment: Appointment): Appointment {
        return appointment
    }
}