package ru.bacha.registration.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import ru.bacha.model.AppointmentDto
import ru.bacha.registration.mapper.AppointmentMapper
import ru.bacha.registration.persistence.model.Appointment
import ru.bacha.registration.persistence.repository.AppointmentRepository

@Service
@RequiredArgsConstructor
class AppointmentServiceImpl(
    final val repository: AppointmentRepository,
    final val mapper: AppointmentMapper
) : AppointmentService {

    override fun createAppointment(dto: AppointmentDto): Appointment {
        return repository.save(mapper.map(dto))
    }

}