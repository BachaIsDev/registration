CREATE TABLE appointment
(
    id               UUID NOT NULL,
    appointment_date TIMESTAMP WITHOUT TIME ZONE,
    doctor_id        UUID,
    appointment_type VARCHAR(20),
    CONSTRAINT pk_appointment PRIMARY KEY (id)
);