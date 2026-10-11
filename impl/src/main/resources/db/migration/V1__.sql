CREATE TABLE users
(
    uuid     UUID         NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (uuid),
    CONSTRAINT uq_users_email UNIQUE (email)
);