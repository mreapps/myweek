BEGIN;

INSERT INTO person (firstname, middlename, lastname, gender, birthday) VALUES
  ('Espen', null, 'Simensen', 1, '1979-10-08'),
  ('Camilla', null, 'Enger', 2, '1983-12-15'),
  ('Vetle', 'Enger', 'Simensen', 1, '2007-09-17'),
  ('Mathilde', 'Enger', 'Simensen', 2, '2011-09-11')
;

INSERT INTO "user" (email_address, encrypted_password, person_uid) VALUES
  ('espen@simensen.priv.no', '123', (select uid from person where firstname='Espen')),
  ('camillaenger@hotmail.com', '123', (select uid from person where firstname='Camilla'));

-- noinspection SqlResolve
INSERT INTO person_admin VALUES
  ((select uid from "user" where email_address='espen@simensen.priv.no'),  (select uid from person where firstname='Vetle')),
  ((select uid from "user" where email_address='espen@simensen.priv.no'),  (select uid from person where firstname='Mathilde')),
  ((select uid from "user" where email_address='camillaenger@hotmail.com'),  (select uid from person where firstname='Vetle')),
  ((select uid from "user" where email_address='camillaenger@hotmail.com'),  (select uid from person where firstname='Mathilde'))
;

END;