SELECT DISTINCT r.mbr, ime, prz, plt
FROM radnik r, radproj rp1
WHERE r.mbr = rp1.mbr AND rp1.brc > (SELECT AVG(brc) FROM radproj rp2 WHERE rp2.spr = rp1.spr);

SELECT mbr, ime, prz, plt, god
FROM radnik r
WHERE NOT EXISTS (SELECT mbr FROM radnik r1 WHERE r1.god < r.god);

SELECT mbr, ime, prz
FROM radnik r
WHERE NOT EXISTS (SELECT mbr FROM radproj r1 WHERE r.mbr = r1.mbr );

SELECT DISTINCT mbr, ime, prz, god
FROM radnik r, projekat p
WHERE r.mbr=p.ruk AND NOT EXISTS(SELECT mbr
								FROM radnik r1, projekat p1
								WHERE r1.mbr=p1.ruk AND r1.god>r.god);

SELECT mbr, ime, prz
FROM radnik r, projekat p
WHERE mbr in (SELECT spr FROM radproj WHERE spr = 20)
UNION
SELECT mbr, ime, prz
FROM radnik r
WHERE r.plt > (SELECT AVG(plt) from radnik);

SELECT mbr, ime, prz
FROM radnik
WHERE mbr IN (SELECT mbr FROM radproj WHERE spr=20)
UNION ALL
SELECT mbr, ime, prz
FROM radnik
WHERE plt > (SELECT AVG(plt) FROM radnik);

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE 'M%' OR prz LIKE 'R%'
INTERSECT
SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE 'M%' OR prz LIKE 'P%';

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE 'M%' OR prz LIKE 'R%'
MINUS
SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE 'M%' OR prz LIKE 'P%';

SELECT ime, prz
FROM radnik NATURAL JOIN radproj
WHERE spr = 30;

SELECT ime, prz
FROM radnik r INNER JOIN radproj rp ON r.mbr = rp.mbr
WHERE spr=30;

SELECT r.mbr, ime, prz, spr
FROM radnik r LEFT OUTER JOIN radproj rp ON r.mbr = rp.mbr;

SELECT r.mbr, ime, prz, NVL(nap, 'Ne rukovodi projektom') Projekat
FROM radnik r LEFT OUTER JOIN projekat p ON r.mbr = p.ruk;

SELECT nap, NVL(mbr, 0) Projekat
FROM radproj rp RIGHT OUTER JOIN projekat p ON rp.spr = p.spr;

SELECT r.mbr, r.prz, r.ime, NVL(p.spr, 0) AS spr, NVL(p.nap, 'Ne postoji') AS nap
FROM radnik r LEFT OUTER JOIN radproj rp ON r.mbr=rp.mbr LEFT OUTER JOIN projekat p ON rp.spr=p.spr
ORDER BY mbr;

SELECT *
FROM radnik CROSS JOIN projekat;

SELECT mbr, ime, prz, plt
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

SELECT mbr, ime, plt, sef
FROM radnik
ORDER BY
CASE
    WHEN sef IS NULL THEN 1
    ELSE 2
END, plt DESC;

WITH projinfo AS (
	SELECT rp.spr, COUNT(rp.mbr) AS rad_broj
	FROM radproj rp GROUP BY rp.spr)
SELECT r.mbr, r.ime, r.prz, rp.spr, pi.rad_broj-1 ostali
FROM radnik r, radproj rp, projinfo pi
WHERE r.mbr=rp.mbr AND rp.spr=pi.spr;

WITH projinfo AS (
    SELECT rp.spr, SUM(rp.brc) AS cas_suma
    FROM radproj rp
    GROUP BY rp.spr)
SELECT r.mbr, r.ime, r.prz, rp.spr, ROUND(rp.brc/pi.cas_suma, 2) udeo
FROM radnik r, radproj rp, projinfo pi
WHERE r.mbr=rp.mbr AND rp.spr=pi.spr;
