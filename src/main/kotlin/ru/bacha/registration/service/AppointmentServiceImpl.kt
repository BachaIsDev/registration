package ru.bacha.registration.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import ru.bacha.registration.persistence.model.Appointment
import ru.bacha.registration.persistence.repository.AppointmentRepository

@Service
@RequiredArgsConstructor
class AppointmentServiceImpl(repository: AppointmentRepository) : AppointmentService {
    final val repository : AppointmentRepository = repository

    override fun createAppointment(appointment: Appointment): Appointment {
        return repository.save(appointment)
    }

}