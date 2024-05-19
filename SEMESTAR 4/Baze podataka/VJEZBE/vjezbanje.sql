CREATE TABLE radnik(
	 Mbr integer NOT NULL,
	 Ime varchar(20) NOT NULL,
	 Prz varchar(25) NOT NULL,
     Sef integer,
	 Plt decimal(10, 2),
     Pre decimal(6, 2),
	 God date NOT NULL,
	 CONSTRAINT radnik_PK PRIMARY KEY (Mbr),
	 CONSTRAINT radnik_FK FOREIGN KEY (Sef) REFERENCES Radnik (Mbr),
     CONSTRAINT radnik_CH CHECK (Plt>500) 
);

CREATE TABLE projekat
	(
	 Spr integer not null,
	 Ruk integer not null,
	 Nap varchar(30),
	 Nar varchar(30),
	 CONSTRAINT projekat_PK PRIMARY KEY (Spr),
	 CONSTRAINT projekat_FK FOREIGN KEY (Ruk) REFERENCES Radnik (Mbr),
     CONSTRAINT projekat_UK UNIQUE (Nap)
);

CREATE TABLE radproj
	(
	 Spr integer NOT NULL,
	 Mbr integer NOT NULL,
	 Brc integer NOT NULL,
	 CONSTRAINT radproj_PK PRIMARY KEY (Spr, Mbr),
	 CONSTRAINT radproj_rad_FK FOREIGN KEY (Mbr) REFERENCES radnik(Mbr),
	 CONSTRAINT radproj_prj_FK FOREIGN KEY (Spr) REFERENCES projekat(Spr)
);

insert into radnik values (10, 'Pera',  'Peric',    NULL, 10000.00, 100.00, to_date('01-01-1987', 'DD-MM-YYYY'));
insert into radnik values (20, 'Milan', 'Milic',    10,   20000.00,   0.00, to_date('01-12-1955', 'DD-MM-YYYY'));
insert into radnik values (30, 'Eva',   'Ras',      10,   15000.00,   NULL, to_date('11-05-1947', 'DD-MM-YYYY'));
insert into radnik values (40, 'Moma',  'Momic',    20,    8000.00,   NULL, to_date('01-06-1933', 'DD-MM-YYYY'));
insert into radnik values (50, 'Rastko','Cvetkovic',20,   40000.00,   NULL, to_date('07-01-1974', 'DD-MM-YYYY'));
insert into radnik values (60, 'Mira',  'Miric',    20,   20000.00,   NULL, to_date('01-07-1945', 'DD-MM-YYYY'));
insert into radnik values (70, 'Savo',  'Oroz',     30,   35000.00,   NULL, to_date('01-01-1933', 'DD-MM-YYYY'));
insert into radnik values (80, 'Petar', 'Petric',   30,    9000.00,   NULL, to_date('31-07-1939', 'DD-MM-YYYY'));
insert into radnik values (90, 'Marko', 'Markovic', 30,    6000.00,   NULL, to_date('01-04-1940', 'DD-MM-YYYY'));
insert into radnik values (100,'Jova',  'Jovic',    50,   32000.00,   NULL, to_date('10-09-1947', 'DD-MM-YYYY'));
insert into radnik values (110,'Laza',  'Lazic',    50,   10000.00,   NULL, to_date('01-01-1978', 'DD-MM-YYYY'));
insert into radnik values (120,'Djoka', 'Djokic',   70,    9500.00,   NULL, to_date('21-10-1970', 'DD-MM-YYYY'));
insert into radnik values (130,'Ziva',  'Zivic',    70,    8700.00,   NULL, to_date('11-11-1948', 'DD-MM-YYYY'));
insert into radnik values (140,'Iva',   'Ivic',     70,   10000.00,   NULL, to_date('31-12-1933', 'DD-MM-YYYY'));
insert into radnik values (150,'Seka',  'Sekic',    70,   20000.00,   NULL, to_date('21-01-1939', 'DD-MM-YYYY'));
insert into radnik values (160,'Ruza',  'Ruzic',    70,   10000.00,   NULL, to_date('13-07-1940', 'DD-MM-YYYY'));
insert into radnik values (170,'Ana',  'Stanic',    70,   20000.00,   NULL, to_date('13-07-1970', 'DD-MM-YYYY'));
insert into radnik values (180,'Eva',  'Tomic',    70,   32000.00,   NULL, 	to_date('20-07-1980', 'DD-MM-YYYY'));
insert into radnik values (190,'Eva',  'Sic',    70,   35000.00,   NULL, 	to_date('20-12-1971', 'DD-MM-YYYY'));
insert into radnik values (200,'Ana',  'Lemic',    70,   35000.00,   NULL, 	to_date('10-12-1975', 'DD-MM-YYYY'));
insert into radnik values (210,'Ana',  'Miric',    70,   35000.00,   NULL, 	to_date('10-12-1975', 'DD-MM-YYYY'));
insert into radnik values (230,'Ena',  'Mirovic',    70,   35000.00,   NULL, to_date('10-12-1975', 'DD-MM-YYYY'));

insert into projekat values (10,100, 'Optimizacija u spicu','TE-TO');
insert into projekat values (20, 70, 'Skladistenje','FADIP');
insert into projekat values (30, 50, 'Optimizacija mreze','ElektroLux');
insert into projekat values (40, 50, 'IIS','Sintelon');
insert into projekat values (50, 70, 'Dijete','KBC');
insert into projekat values (60,100, 'Ziro racuni','Dafiscan');
insert into projekat values (70,100, 'IIS*Case','DAS');
insert into projekat values (80,100, 'Data Warehouse','Minel');

insert into radproj values (30, 50, 5);
insert into radproj values (40, 50, 2);
insert into radproj values (20, 70, 8);
insert into radproj values (50, 70, 1);
insert into radproj values (10,100, 9);
insert into radproj values (60,100,11);
insert into radproj values (10, 10,10);
insert into radproj values (10, 50,11);
insert into radproj values (10,130, 2);
insert into radproj values (20,120, 7);
insert into radproj values (20,110, 8);
insert into radproj values (20, 20,13);
insert into radproj values (30, 30, 3);
insert into radproj values (30, 10, 4);
insert into radproj values (30, 60, 4);
insert into radproj values (30, 80, 9);
insert into radproj values (40, 90, 1);
insert into radproj values (40, 30, 1);
insert into radproj values (40,110, 8);
insert into radproj values (40,120, 4);
insert into radproj values (50,120, 1);
insert into radproj values (50, 60, 3);
insert into radproj values (60, 40, 1);
insert into radproj values (60,140, 1);
insert into radproj values (60,150, 1);

CREATE TABLE faze_projekta
(
    sfp integer not null,
    spr integer not null,
    rukfp integer,
    nafp varchar(30),
    datp date,
    CONSTRAINT faze_projekta_pk PRIMARY KEY (sfp, spr),
    CONSTRAINT faze_projekta_fk1 FOREIGN KEY (spr) REFERENCES projekat(spr),
	CONSTRAINT faze_projekta_fk2 FOREIGN KEY (rukfp) REFERENCES radnik(mbr),
	CONSTRAINT faze_projekta_uk UNIQUE(nafp)
);

ALTER TABLE faze_projekta
	ADD datz date
	ADD CONSTRAINT date_check CHECK(datp<=datz);

DROP TABLE faze_projekta;

SELECT * FROM radnik;
SELECT * FROM projekat;
SELECT * FROM radproj;

SELECT ime, prz
FROM radnik;

SELECT DISTINCT ime, prz
FROM radnik;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE plt > 25000;

SELECT mbr, ime, prz, plt, 12*plt
FROM radnik;

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NULL;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE plt >= 20000 and plt <= 24000;

SELECT ime, prz, god
FROM radnik
WHERE god NOT BETWEEN '01-JAN-1973' AND '31-DEC-1980';

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE '%c';

SELECT mbr, ime, prz
FROM radnik
WHERE ime LIKE '_a%';

SELECT DISTINCT ime
FROM radnik
WHERE ime LIKE 'E%';

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE '%e%' OR prz LIKE '%E%';

SELECT mbr, ime, prz
FROM radnik
WHERE ime LIKE 'A%';

SELECT DISTINCT mbr
FROM radproj rp
WHERE spr IN (10,20,30);

SELECT DISTINCT mbr
FROM radproj
WHERE spr='10' OR brc IN (2,4,6);

SELECT mbr, ime, prz
FROM radnik
WHERE ime NOT IN ('Ana', 'Sanja');

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NOT NULL
ORDER BY prz ASC;

SELECT mbr, ime, prz, PLT
FROM radnik
ORDER BY plt DESC;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE ime IN ('Moma', 'Pera');

SELECT mbr, ime, prz, plt
FROM radnik
WHERE ime NOT IN ('Moma', 'Pera');

SELECT mbr, plt+NVL(pre, 0), pre, plt
FROM radnik;

SELECT COUNT(*) radnici
FROM radnik;

SELECT COUNT(DISTINCT sef) sefovi 
FROM radnik;

SELECT min(plt) minimalna_plt, max(plt) maximalna_plt
FROM radnik; 

SELECT COUNT(*) radnici, SUM(plt)
FROM radnik;

SELECT COUNT(*) radnici, AVG(plt), SUM(12*plt)
FROM radnik;

SELECT ROUND(AVG(plt*1.41),2)
FROM radnik;

SELECT COUNT(rp.mbr), rp.spr, SUM(rp.brc)
FROM radproj rp
GROUP BY rp.spr;

SELECT mbr, COUNT(spr)
FROM radproj
GROUP BY mbr
HAVING COUNT(spr) > 2;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE plt > (SELECT AVG(plt) FROM radnik)
ORDER BY PLT ASC;

SELECT mbr, ime, prz
FROM radnik
WHERE mbr IN 
	(SELECT mbr FROM radproj WHERE spr=10)
AND mbr NOT IN
	(SELECT mbr FROM radproj WHERE spr=30);

SELECT ime, prz, god
FROM radnik
WHERE god <= (SELECT MIN(god) FROM radnik);

SELECT p.nap
FROM projekat p
WHERE spr in (SELECT spr
			  FROM radproj
			  WHERE mbr IN (SELECT mbr
							FROM radproj
							WHERE spr=60));

SELECT * 
FROM radnik
WHERE ROWNUM <= 10
ORDER BY plt DESC;

SELECT r.mbr, prz, ime, plt, brc
FROM radnik r, radproj rp
WHERE r.mbr=rp.mbr AND rp.spr=10;

SELECT DISTINCT r.mbr, ime, prz, plt
FROM radnik r, projekat p
WHERE p.ruk=r.mbr;

SELECT ime, prz, COUNT(spr)
FROM radnik r, projekat p
WHERE p.ruk=r.mbr
GROUP BY ime, prz;

SELECT r.mbr, ime, prz, COUNT(*), SUM(brc)
FROM radnik r, radproj rp
WHERE r.mbr=rp.mbr
GROUP BY r.mbr, ime, prz;

SELECT mbr, ime, prz
FROM radnik r, projekat p
WHERE p.spr=10 AND r.mbr!=p.ruk;

SELECT ime, prz, COUNT(rp.spr)
FROM radnik r, radproj rp
WHERE r.mbr=rp.mbr AND r.mbr IN (SELECT ruk FROM projekat)
GROUP BY ime, prz;

SELECT p.nap, SUM(rp.brc)
FROM projekat p, radproj rp
WHERE p.spr=rp.spr
GROUP BY p.nap
HAVING SUM(rp.brc) > 15;

SELECT p.spr, p.nap, COUNT(rp.mbr)
FROM projekat p, radproj rp
WHERE p.spr=rp.spr 
GROUP BY p.spr, p.nap
HAVING COUNT(rp.mbr) > 2;

SELECT p.nap, p.spr
FROM projekat p, radproj rp
WHERE p.spr=rp.spr
GROUP BY p.nap, p.spr
HAVING AVG(rp.brc) > (SELECT AVG(brc) FROM radproj);

SELECT p.nap, p.spr
FROM projekat p, radproj rp
WHERE p.spr=rp.spr
GROUP BY p.nap, p.spr
HAVING AVG(rp.brc) >= ALL(SELECT AVG(brc) FROM radproj GROUP BY spr);

SELECT r.mbr, r.ime, r.prz, r.plt
FROM radnik r, radnik rr
WHERE rr.mbr=40 AND r.plt > rr.plt;

SELECT r1.ime, r1.prz, r1.plt, p.nap
FROM radnik r2, radproj rp, projekat p, radnik r1
WHERE rp.mbr=r1.mbr AND rp.spr=p.spr AND p.ruk=r2.mbr AND r1.plt+1000<r2.plt;

SELECT DISTINCT r.mbr, ime, prz, plt
FROM radnik r, radproj rp1
WHERE r.mbr=rp1.mbr AND rp1.brc > (SELECT AVG(brc) FROM radproj rp2 WHERE rp2.spr=rp1.spr);

SELECT ime, prz
FROM radnik r
WHERE r.god <= (SELECT min(god) FROM radnik);

SELECT r.mbr, r.ime, r.prz
FROM radnik r
WHERE r.mbr NOT IN(
    SELECT mbr FROM radproj
);

SELECT r.mbr, r.ime, r.prz
FROM radnik r
WHERE NOT EXISTS(
    SELECT * FROM radproj rp WHERE rp.mbr=r.mbr AND rp.spr=10
);

SELECT r.mbr, r.ime, r.prz
FROM radnik r
WHERE NOT EXISTS (
    SELECT * FROM projekat p WHERE r.mbr=p.ruk
);

SELECT DISTINCT r.mbr, r.ime, r.prz
FROM radnik r, projekat p
WHERE r.mbr=p.ruk AND NOT EXISTS(SELECT mbr
								FROM radnik r1, projekat p1
								WHERE r1.mbr=p1.ruk AND r1.god>r.god);

SELECT r.mbr, r.ime, r.prz 
FROM radnik r
WHERE r.mbr IN (SELECT mbr FROM radproj WHERE spr=20)
UNION
SELECT r.mbr, r.ime, r.prz
FROM radnik r
WHERE plt > (SELECT AVG(plt) FROM radnik);

SELECT r.mbr, r.ime, r.prz 
FROM radnik r
WHERE r.prz LIKE 'M%' OR r.prz LIKE 'P%'
INTERSECT
SELECT r.mbr, r.ime, r.prz 
FROM radnik r
WHERE r.prz LIKE 'M%' OR r.prz LIKE 'R%';

SELECT ime, prz
FROM radnik r, radproj rp
WHERE r.mbr=rp.mbr and rp.spr=30;

SELECT r.mbr, ime, prz, NVL(rp.spr, 0)
FROM radnik r LEFT OUTER JOIN radproj rp ON rp.mbr=r.mbr;

SELECT p.nap, NVL(rp.mbr, 0)
FROM projekat p LEFT OUTER JOIN radproj rp ON rp.spr=p.spr;

SELECT r.mbr, r.ime, r.prz, NVL(p.spr, 0), NVL(p.nap, 'Ne postoji')
FROM radnik r LEFT OUTER JOIN radproj rp ON rp.mbr=r.mbr LEFT OUTER JOIN projekat p ON p.spr=rp.spr;

SELECT r.mbr, r.ime, r.prz, p.spr
FROM radnik r LEFT OUTER JOIN radproj rp ON rp.mbr=r.mbr LEFT OUTER JOIN projekat p ON p.spr=rp.spr;

SELECT r.ime, r.prz, NVL(rr.prz, 'Nema sefa')
FROM radnik r, radnik rr
WHERE r.sef=rr.mbr;

SELECT brc, COUNT(mbr)
FROM radproj GROUP BY brc
ORDER BY brc DESC;

WITH rukovodioci AS (
    SELECT r.mbr, r.ime, r.prz, COUNT(p.spr) broj_proj
	FROM radnik r, projekat p
    WHERE r.mbr=p.ruk
    GROUP BY r.mbr, r.ime, r.prz
), radnici AS (
    SELECT AVG(COUNT(spr)) broj
    FROM radproj rp, radnik r
    WHERE rp.mbr = r.mbr
    AND prz NOT LIKE '%ic'
    GROUP BY r.mbr
)
SELECT k.mbr, k.ime, k.prz, k.broj_proj
FROM rukovodioci k, radnici r
WHERE k.broj_proj <= r.broj;

SELECT mbr, ime, plt,
CASE 
    WHEN plt < 10000 THEN 'mala primanja'
    WHEN plt >=10000 AND plt < 20000 THEN 'srednja primanja'
    WHEN plt >=20000 AND plt < 40000 THEN 'visoka primanja'
    ELSE 'izuzetno visoka primanja'
END AS visina_primanja
FROM radnik
ORDER BY
CASE visina_primanja
	WHEN 'izuzetno visoka primanja' THEN 1
	WHEN 'visoka primanja' THEN 2
	WHEN 'srednja primanja' THEN 3
	ELSE 4
END DESC, plt ASC;

SELECT mbr, ime, prz, plt, sef
FROM radnik
ORDER BY 
CASE
	WHEN sef IS NOT NULL THEN 2
	ELSE 1
END, plt desc;

WITH radnici AS (
    SELECT spr, COUNT(mbr) as ostali
    FROM radproj rp
    GROUP BY spr
)
SELECT r.mbr, r.ime, r.prz, rp.spr, p.ostali-1 ostali
FROM radnici p, radproj rp, radnik r
WHERE r.mbr=rp.mbr AND rp.spr=p.spr;

WITH udio AS (
    SELECT rp.spr, SUM(rp.brc) as ukupno
    FROM  radproj rp
    GROUP BY rp.spr
)
SELECT r.mbr, r.ime, r.prz, u.spr, ROUND((rp.brc/u.ukupno), 2)
FROM radnik r, udio u, radproj rp
WHERE r.mbr=rp.mbr AND rp.spr=u.spr;

WITH rukovodioci AS (
    SELECT r.mbr, r.ime, r.prz, p.spr
    FROM radnik r, projekat p
    WHERE p.ruk=r.mbr
), radnici AS (
    SELECT spr, COUNT(mbr) ljudi 
    FROM radproj
    GROUP BY spr
)
SELECT r.mbr, r.ime, r.prz, rr.ljudi
FROM rukovodioci r, radnici rr
WHERE r.spr=rr.spr
GROUP BY r.mbr, r.ime, r.prz, rr.ljudi;

WITH prosjek AS (
    SELECT spr, AVG(brc) ppp
    FROM radproj
    GROUP BY spr
)
SELECT r.mbr, r.ime, r.prz, r.plt
FROM radnik r, prosjek p, radproj rp
WHERE rp.mbr=r.mbr AND p.spr=rp.spr AND rp.brc > p.ppp
GROUP BY r.mbr, r.ime, r.prz, r.plt;

WITH angaz_po_radnicima (mbr, sbrc) AS (
    SELECT r.mbr, nvl(SUM(rp.brc), 0)
    FROM radnik r, radproj rp
    WHERE r.mbr = rp.mbr (+)
    GROUP BY r.mbr),
angaz_sefova (mbr, prz, ime, brrad, brsat) AS (
    SELECT distinct r.sef, r1.prz, r1.ime, count(*), a.sbrc
    FROM radnik r, radnik r1, angaz_po_radnicima a
    WHERE r.Sef = r1.Mbr AND r.Sef = a.Mbr
    GROUP BY r.Sef, r1.Prz, r1.Ime, a.SBrc)
SELECT SUM(brsat) AS ukangsef
FROM angaz_sefova;

CREATE OR REPLACE VIEW radnici(ime, prezime, plata) AS
SELECT ime, prz, plt
FROM radnik;

CREATE OR REPLACE VIEW angazovanje_radnika(mbr, sati) AS
SELECT r.mbr, NVL(SUM(brc), 0)
FROM radnik r LEFT OUTER JOIN radproj rp ON r.mbr=rp.mbr
GROUP BY r.mbr;

CREATE VIEW angazovanje_sefova(mbr, ime, prezime, br_rad, br_sat) AS
SELECT r.sef, r1.ime, r1.prz, COUNT(*), a.sati
FROM radnik r, radnik r1, angazovanje_radnika a
WHERE r.sef=r1.mbr AND r.sef=a.mbr
GROUP BY r.Sef, r1.Prz, r1.Ime, a.sati;

SELECT SUM(br_sat) as sati
FROM angazovanje_sefova;

SELECT *
FROM radnik
WHERE prz LIKE SUBSTR(IME, 0, 3) || '%';

SELECT TRIM(TRAILING 'a' FROM ime)
FROM radnik;

UPDATE radnik
SET ime = SUBSTR(ime, 1, LENGTH(ime)-1) || UPPER(SUBSTR(ime, LENGTH(ime), 1));

SELECT Mbr,
Ime || ' ' || Prz "Ime i prezime",
Plt * 1.17 Plata
FROM Radnik;

SELECT *
FROM radnik
WHERE LOWER(prz) LIKE '%' || LOWER(ime) || '%';
