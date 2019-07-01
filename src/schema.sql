--CREATE DATABASE brighttalk;

USE brighttalk;

CREATE TABLE IF NOT EXISTS realm (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  realm_name VARCHAR(255) NOT NULL UNIQUE,
  realm_description VARCHAR(255),
  encryption_key CHAR(32) NOT NULL
);


  /*
  id Unique ID. Primary key. System-generated.
  name Realm name (alias for ID). Must be unique.
  description Realm description. Up to 255 chars.
  key Realm encryption key. Fixed length 32 chars.
*/