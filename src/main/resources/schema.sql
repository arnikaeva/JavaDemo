CREATE TABLE IF NOT EXISTS Realm (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR NOT NULL UNIQUE,
  description VARCHAR(255),
  key CHAR(32) NOT NULL,
  PRIMARY KEY(id)
)


  /*
  id Unique ID. Primary key. System-generated.
  name Realm name (alias for ID). Must be unique.
  description Realm description. Up to 255 chars.
  key Realm encryption key. Fixed length 32 chars.
*/