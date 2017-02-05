BEGIN;

DROP TABLE IF EXISTS person_admin;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS person;

CREATE TABLE person
(
  uid        SERIAL       NOT NULL,
  firstname  VARCHAR(100) NOT NULL,
  middlename VARCHAR(100),
  lastname   VARCHAR(100) NOT NULL,
  gender     INTEGER,
  birthday   DATE,
  PRIMARY KEY (uid)
);

CREATE TABLE "user"
(
  uid                SERIAL       NOT NULL,
  email_address      VARCHAR(255) NOT NULL UNIQUE,
  encrypted_password VARCHAR(255) NOT NULL,
  person_uid         INTEGER      NOT NULL,
  PRIMARY KEY (uid)
);

ALTER TABLE "user"
  ADD CONSTRAINT fk_user_person FOREIGN KEY (person_uid) REFERENCES person (uid);

CREATE TABLE person_admin
(
  user_uid   INTEGER NOT NULL,
  person_uid INTEGER NOT NULL,
  PRIMARY KEY (user_uid, person_uid)
);
ALTER TABLE person_admin
  ADD CONSTRAINT fk_person_admin_user FOREIGN KEY (user_uid) REFERENCES "user" (uid);
ALTER TABLE person_admin
  ADD CONSTRAINT fk_person_admin_person FOREIGN KEY (person_uid) REFERENCES person (uid);

END;