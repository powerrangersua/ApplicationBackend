BEGIN;
INSERT INTO users (username, password, name, surname, age) VALUES ('mak.muzychuk@gmail.com', 'dataart', 'Max', 'Muzychuk', 23);
INSERT INTO users (username, password, name, surname, age) VALUES ('user2', 'user2pass', 'UserName2', 'UserSurname2', 10);
INSERT INTO users (username, password, name, surname, age) VALUES ('user3', 'user3pass', 'UserName3', 'UserSurname3', 20);
COMMIT;