--DROP TABLE IF EXISTS husband;
--DROP TABLE IF EXISTS wife;
--DROP TABLE IF EXISTS team;


--CREATE TABLE husband (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(250) NOT NULL
--);
--
--CREATE TABLE wife (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(250) NOT NULL
--);
--
--CREATE TABLE team (
--  id INT AUTO_INCREMENT  PRIMARY KEY,
--  name VARCHAR(250) NOT NULL
--);

INSERT INTO husband (name, beers) VALUES
  ('Pista', 0),
  ('Jozsi', 0),
  ('Laci', 0),
  ('Geza', 0),
  ('Feri', 0),
  ('Tarzan', 0),
  ('John', 0),
  ('Bill', 0),
  ('Vladislav', 0),
  ('Szergej', 0);

INSERT INTO wife (name, FREE_TIME) VALUES
  ('Erzsi', 0),
  ('Kati', 0),
  ('Szamanta', 0),
  ('Dzsenifer', 0),
  ('Erika', 0),
  ('Klaudia', 0),
  ('Zsanett', 0),
  ('Szandra', 0),
  ('Kriszti', 0),
  ('Anett', 0);

INSERT INTO team (name) VALUES
  ('Portugalia'),
  ('Magyarorszag'),
  ('Hollandia'),
  ('Horvatorszag'),
  ('Brazilia'),
  ('Franciaorszag'),
  ('Anglia'),
  ('Nemetorszag'),
  ('Norvegia'),
  ('Spanyolorszag'),
  ('Olaszorszag');