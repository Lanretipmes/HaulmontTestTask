insert into PATIENT (id, NAME, SURNAME, patronymic, PHONE)values (1, 'Leon', 'Yurocjkin', 'Arrighetti', '55863436981');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Artemis', 'Ossenna', 'Coulman', '30858881564');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Kris', 'Gatley', 'Thomasen', '42066281002');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Wolfy', 'Mallinson', 'Tossell', '12429119336');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Rhett', 'Tregonna', 'Fardy', '82553122107');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Johnny', 'Coslitt', 'Sauniere', '40246183017');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Ronalda', 'Esel', 'Querrard', '65861912412');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Megen', 'Boyett', 'Antoniak', '98683617279');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Ashely', 'Copper', 'Blenkinsop', '60032187181');
insert into PATIENT (NAME, SURNAME, patronymic, PHONE)values ('Allis', 'Dunford', 'Polin', '95131010029');

insert into DOCTOR (id, NAME, SURNAME, patronymic, specialty)values (1, 'Zacherie', 'Housbie', 'MacCahee', 'SURGEON');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Slade', 'Pantin', 'Barukh', 'DENTIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Ermengarde', 'Garham', 'Genty', 'THERAPIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Ettore', 'Batchley', 'Gutch', 'NEUROLOGIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Vassily', 'Preist', 'Rose', 'DENTIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Aluin', 'Fantonetti', 'Jagson', 'DENTIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Galen', 'Bosman', 'Eldridge', 'THERAPIST');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Georgy', 'Colbridge', 'Nussen', 'SURGEON');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Ardith', 'Chace', 'Medina', 'SURGEON');
insert into DOCTOR (NAME, SURNAME, patronymic, specialty)values ('Zilvia', 'Evins', 'Bellenie', 'DENTIST');

insert into RECIPE (id, description, patient_id, doctor_id, creation_date, validity, priority) values (1, 'dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi', 8, 5, '2019-08-19', 2,'NORMAL');
insert into RECIPE (description, patient_id, doctor_id, creation_date, validity, priority) values ('cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum', 3, 3, '2019-09-08', 1,'NORMAL');
