package ru.bacha.registration.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import ru.bacha.model.AppointmentDto
import ru.bacha.registration.persistence.model.Appointment
import ru.bacha.registration.persistence.model.AppointmentType
import ru.bacha.registration.persistence.repository.AppointmentRepository
import java.time.LocalDateTime
import java.util.*

@Service
@RequiredArgsConstructor
class AppointmentServiceImpl(repository: AppointmentRepository) : AppointmentService {
    final val repository : AppointmentRepository = repository

    override fun createAppointment(dto: AppointmentDto): Appointment {
//        val toSave = Appointment();
//        return repository.save(dto)
        return Appointment(UUID.randomUUID(), LocalDateTime.now(),UUID.randomUUID(),AppointmentType.DISEASE,null)
    }

}