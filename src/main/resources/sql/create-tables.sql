CREATE SCHEMA IF NOT EXISTS logging;
CREATE TABLE logging.main (
  id int PRIMARY KEY ,
  value VARCHAR(256)
);

CREATE TABLE logging.copy (
  id int PRIMARY KEY ,
  value VARCHAR(256)
);

CREATE TABLE logging.history (
  id_time TIMESTAMP PRIMARY KEY ,
  query VARCHAR(2000) ,
  flag BOOLEAN
);