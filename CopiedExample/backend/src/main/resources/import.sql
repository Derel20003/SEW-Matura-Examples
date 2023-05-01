
insert into COURSE(ID, TITLE, DESCRIPTION, PREREQUISITE) values
 ('PRO001', 'Grundkurs Programmieren', 'Basics der Programmierung, einfache Algorithmen', NULL),
 ('PRO002', 'Algorithmen', 'Div. Standard-Algorithmen', 'PRO001'),
 ('P3D001', 'Grundlagen 3D-Programmierung', 'Algorithmen und Vorgehensweise für 3D-Rendering', 'PRO001'),
 ('P3D002', 'Advanced 3D-Programmierung', 'komplexe Transformationen, Shading, etc', 'P3D001'),

 ('DBG001', 'Grundlagen Datenbanktheorie', 'Begriffsdefinitionen, 3-Ebenen-Modell, Schlüsselkonzept, ...', NULL),
 ('SQL001', 'SQL Grundkurs', 'DDL und DQL Statements, einfache Joins, Gruppierungen', 'DBG001'),
 ('ERD001', 'Datenmodellierung', 'Erstellung ERDs und Relationalen Modellen', 'DBG001'),
 ('SQL002', 'Advanced SQL', 'Abfragen mit erhöhtem Komplexitätsgrad, hierarchische Abfragen', 'DBG001');

insert into PERSON(ID, FIRSTNAME, LASTNAME) values
 (1, 'Max', 'Muster'),
 (2, 'Susi', 'Sonne'),
 (3, 'Hans', 'Huber'),
 (4, 'Karl', 'Kaufmann'),
 (5, 'Franz', 'Forchner');

insert into COURSEPLAN(ID, COURSE_ID, STARTDATE, ENDDATE, LOCATION, TEACHER) values
 (1, 'PRO001', '2023-01-10', '2023-05-30', 'Leonding', 1),
 (2, 'PRO001', '2023-08-01', '2023-12-20', 'Leonding', 1),
 (3, 'PRO002', '2023-06-01', NULL, 'Linz', 1),
 (4, 'P3D001', '2023-06-01', NULL, 'Leonding', 1),
 (5, 'DBG001', '2023-01-10', '2023-05-30', 'Leonding', 2),
 (6, 'SQL001', '2023-06-01', '2023-12-20', 'Leonding', 2);

drop sequence hibernate_sequence RESTRICT ;
create sequence hibernate_sequence start with 100;