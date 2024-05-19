ima jos prije ovoga !!!


WITH rukovodilac AS (
    SELECT mbr, ime, prz, spr
    FROM radnik, projekat 
    WHERE mbr = ruk
), projinfo AS (
    SELECT spr, COUNT(mbr) ljudi 
    FROM radproj
    GROUP BY spr
)
SELECT ru.mbr, ru.ime, ru.prz, SUM(pi.ljudi) ljudi
FROM rukovodilac ru, projinfo pi
WHERE ru.spr = pi.spr
GROUP BY ru.mbr, ru.ime, ru.prz;

CREATE OR REPLACE VIEW plate_radnika(ime, prezime, plata) AS
SELECT ime, prz, plt
FROM radnik;

CREATE OR REPLACE VIEW angazovanje_radnika(mbr, ukupan_broj_sati) AS
SELECT r.mbr, NVL(SUM(rp.brc), 0)
FROM radnik r, radproj rp
WHERE r.mbr = rp.mbr (+)
GROUP BY r.mbr;

CREATE OR REPLACE VIEW sefovi(mbr, ime, prezime, ukupan_broj_ljudi, ukupan_broj_sati) AS
SELECT r.Sef, r1.Prz, r1.Ime, COUNT(*), a.ukupan_broj_sati
FROM radnik r, radnik r1, angazovanje_radnika a
WHERE r.Sef = r1.Mbr AND r.Sef = a.Mbr
GROUP BY r.Sef, r1.Prz, r1.Ime, a.ukupan_broj_sati;

SELECT SUM(r.ukupan_broj_sati)
FROM  sefovi r;

SELECT table_name FROM user_tables;

SELECT DISTINCT object_type FROM user_objects;

SELECT * FROM user_catalog;

SELECT Mbr, Prz, Ime
FROM Radnik
WHERE UPPER(Prz) = 'PETRIC';

SELECT *
FROM radnik
WHERE prz LIKE
SUBSTR(IME, 0, 3) || '%';

UPDATE radnik
SET ime = SUBSTR(ime, 1, LENGTH(ime)-1) || UPPER(SUBSTR(ime, LENGTH(ime), 1));
SELECT * FROM radnik;

SELECT UPPER(ime), LOWER(prz), NVL(TO_CHAR(spr), 'Ne radi na projektu') broj_projekta
FROM radnik LEFT OUTER JOIN radproj on radnik.mbr = radproj.mbr;

SELECT TO_CHAR(god, 'yyyy/mm/dd')
FROM radnik;
