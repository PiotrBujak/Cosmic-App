/*ROLES*/
insert into role (role_id, role)
values (1, 'ADMIN');
insert into role (role_id, role)
values (2, 'USER');

/*USERS*/
insert into user (id, active, name, password)
values (1, 1, 'ADMIN', '$2a$10$vz.EgfxtT8Bb/v/dy/rnyOMtl1O3t/caIZVAat1JapXZFIgiHSuYS');
insert into user (id, active, name, password)
values (2, 1, 'USER', '$2a$10$FgANR8aoABnSbJ82uxcIQOWd3U/Bh3BZy8SxxGSoTBVTHrO3SbowW');

/*ADD ROLE TO USER*/
insert into user_role (user_id, role_id)
values (1, 1);
insert into user_role (user_id, role_id)
values (1, 2);

insert into user_role (user_id, role_id)
values (2, 2);