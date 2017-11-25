DROP TABLE IF EXISTS user_role, roles, users;

CREATE TABLE users (
  USER_ID  INT(11) NOT NULL AUTO_INCREMENT,
  login    VARCHAR(255),
  password VARCHAR(255),
  name     VARCHAR(255),
  surname  VARCHAR(255),
  age      INT,
  PRIMARY KEY (USER_ID)
)
  ENGINE = innoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
  id   INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(45)      DEFAULT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id INT(11) NOT NULL,
  role_id INT(11) NOT NULL,
  PRIMARY KEY (user_id, role_id),
  KEY fk_user_role_roleid_idx (role_id),
  CONSTRAINT fk_user_role_roleid FOREIGN KEY (role_id) REFERENCES roles (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_user_role_userid FOREIGN KEY (user_id) REFERENCES users (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;