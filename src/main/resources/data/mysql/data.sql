BEGIN;
INSERT INTO users (login, password, name, surname, age) VALUES ('mak.muzcyhuk@gmail.com', 'qwerty' , 'Max', 'Muzychuk', 23);
INSERT INTO users (login, password, name, surname, age) VALUES ('user2@gmail.com', 'pass2', 'UserName2', 'UserSurname2', 10);
INSERT INTO users (login, password, name, surname, age) VALUES ('user3@gmail.com', 'pass3', 'UserName3', 'UserSurname3', 20);
INSERT INTO roles VALUES (1, 'ROLE_USER');
COMMIT;