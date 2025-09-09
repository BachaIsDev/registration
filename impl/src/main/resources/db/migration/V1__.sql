CREATE TABLE appointment
(
    id               UUID NOT NULL,
    appointment_date TIMESTAMP WITHOUT TIME ZONE,
    doctor_id        UUID,
    type             SMALLINT,
    CONSTRAINT pk_appointment PRIMARY KEY (id)
);