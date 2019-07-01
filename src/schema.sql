--CREATE DATABASE brighttalk;

USE brighttalk;

CREATE TABLE IF NOT EXISTS realm (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  realm_name VARCHAR(255) NOT NULL UNIQUE,
  realm_description VARCHAR(255),
  encryption_key CHAR(32) NOT NULL
);