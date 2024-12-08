-- integration-test db initial data
insert into student (name, surname) values ('Thomas', 'Huxley');

insert into lecturer (name, surname) values ('James', 'Ross');

insert into student_lecturers (student_id, lecturer_id) values (1, 1);

