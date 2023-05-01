insert into courttype(id, description) values (1, 'Tennis');
insert into courttype(id, description) values (2, 'Badminton');
insert into courttype(id, description) values (3, 'Squash');


insert into court(id, location, courttype_id) values (1, 'Halle A', 1);
insert into court(id, location, courttype_id) values (2, 'Halle A', 1);
insert into court(id, location, courttype_id) values (3, 'Halle A', 1);
insert into court(id, location, courttype_id) values (4, 'Halle B', 2);
insert into court(id, location, courttype_id) values (5, 'Halle B', 2);
insert into court(id, location, courttype_id) values (6, 'Halle B', 2);
insert into court(id, location, courttype_id) values (7, 'Halle B', 2);
insert into court(id, location, courttype_id) values (8, 'Halle B', 3);
insert into court(id, location, courttype_id) values (9, 'Halle B', 3);


insert into customer(id, first_name, last_name, street, house_number, zip_code, city, password) values (-1, 'Max', 'Muster', 'Testweg', '123', '4020', 'Linz', 'passme');

insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (11, -1, 4, '2023-09-01 15:00:00', '2023-09-01 16:00:00', '2020-08-28 17:25:14');
insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (12, -1, 4, '2023-09-03 15:00:00', '2023-09-03 16:00:00', '2020-08-28 17:30:14');
insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (31, -1, 6, '2020-10-03 18:00:00', '2020-10-03 18:30:00', '2020-08-28 17:32:14');

insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (51, -1, 1, '2020-10-04 18:00:00', '2020-10-04 19:00:00', '2020-08-28 17:32:14');
insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (52, -1, 2, '2020-10-04 17:00:00', '2020-10-04 19:00:00', '2020-08-28 17:32:14');
insert into reservation(id, customer_id, court_id, start_time, end_time, timestamp) values (53, -1, 3, '2020-10-04 18:30:00', '2020-10-04 19:30:00', '2020-08-28 17:32:14');