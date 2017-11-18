BEGIN;
INSERT INTO users (name, surname, age) VALUES ('Max', 'Muzychuk', 23);
INSERT INTO users (name, surname, age) VALUES ('UserName2', 'UserSurname2', 10);
INSERT INTO users (USER_ID, name, surname, age) VALUES (10, 'UserName3', 'UserSurname3', 20);
COMMIT;