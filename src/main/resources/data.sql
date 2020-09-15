insert into PATIENT (id, NAME, SURNAME, patronymic, PHONE)values (1, 'Oleg', 'Sokolov', 'Valerievich', '18574068396');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Artemy', 'Lebedev', 'Andreevich', '75295782657');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Sacramar', 'Mironov', 'Andreevich', '57294758192');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Illidan', 'Stormrage', '', '66666666666');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Anatoly', 'Micengendler', 'Marginalovich', '85793847592');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Yevgeny', 'Svetodidov', 'Sarmatovich', '73957385924');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Calvin', 'Candy', '', '83957648695');

insert into DOCTOR (id, NAME, SURNAME, patronymic, specialty)values (1, 'Yevgeny', 'Ponasenkov', 'Nikolayevich', 'SURGEON');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Emil', 'Kurtz', 'Cringewaldovich', 'PSYCHIATRIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Cringewald', 'Kurtz', 'Lebowsky', 'THERAPIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('King', 'Shultz', '', 'DENTIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Zigmund', 'Freud', '', 'NEUROLOGIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Stevie', 'Wonder', '', 'OPHTHALMOLOGIST');

insert into RECIPE (id, description, patient_id, doctor_id, creation_date, validity, priority) values (1, 'To outplay, destroy', 1, 1, '2020-09-10', 1,'STATIM');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('Haloperidol, intensive psychotherapy', 3, 2, '2020-09-11', 6,'NORMAL');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('Caries removal surgery', 7, 4, '2020-09-08', 1,'CITO');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('Deep psychoanalisys', 2, 5, '2020-09-09', 6,'NORMAL');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('Eye prosthetics surgery', 4, 6, '2020-09-11', 1,'STATIM');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('Weekly examination', 5, 3, '2020-09-08', 3,'NORMAL');
