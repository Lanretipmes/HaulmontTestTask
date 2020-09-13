drop table RECIPE if exists;
drop table PATIENT if exists;
drop table DOCTOR if exists;

create table PATIENT
(
    ID               BIGINT IDENTITY
                constraint PATIENT_PK
                          primary key,
    NAME       VARCHAR(50) not null,
    SURNAME    VARCHAR(50) not null,
    PATRONYMIC VARCHAR(50) not null,
    PHONE      VARCHAR(11) not null
);

create table DOCTOR
(
    ID                    BIGINT IDENTITY
                     constraint DOCTOR_PK
                              primary key,
    NAME           VARCHAR(50) not null,
    SURNAME        VARCHAR(50) not null,
    PATRONYMIC     VARCHAR(50) not null,
    SPECIALTY       VARCHAR(50) not null
);

create table RECIPE
(
    ID                  BIGINT IDENTITY
        constraint TABLE_NAME_PK
            primary key,
    DESCRIPTION   VARCHAR(200) not null,
    PATIENT_ID          BIGINT not null
        constraint RECIPE_PATIENT_ID_FK
            references PATIENT,
    DOCTOR_ID           BIGINT not null
        constraint RECIPE_DOCTOR_ID_FK
            references DOCTOR,
    CREATION_DATE                  DATE,
    VALIDITY            BIGINT not null,
    PRIORITY      VARCHAR(20) not null
);




