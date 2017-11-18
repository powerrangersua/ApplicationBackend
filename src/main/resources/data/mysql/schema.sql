DROP TABLE IF EXISTS Users;

CREATE TABLE users(
  USER_ID bigint(20) unsigned NOT NULL auto_increment,
  name VARCHAR(20),
  surname VARCHAR(20),
  age INT,
  PRIMARY KEY (USER_ID)
) ENGINE=innoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;