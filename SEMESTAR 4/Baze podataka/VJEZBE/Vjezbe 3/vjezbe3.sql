SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NOT NULL
ORDER BY prz ASC;

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NOT NULL
ORDER BY prz, ime;

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NOT NULL
ORDER BY prz ASC, ime  DESC;

SELECT mbr, ime, prz
FROM radnik
ORDER BY 2, 3, plt;

SELECT mbr, ime, prz, plt
FROM radnik
ORDER BY plt DESC;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE ime = ANY('Pera', 'Moma');

SELECT mbr, ime, prz, plt
FROM radnik
WHERE ime != ALL('Pera', 'Moma');

SELECT mbr, plt + NULL
FROM radnik;

SELECT mbr, plt + pre
FROM radnik;

SELECT mbr, plt + NVL(pre, 0)
FROM radnik;

SELECT COUNT(*)
FROM radnik;

SELECT COUNT(DISTINCT sef) broj_sefova
FROM radnik;

SELECT MIN(plt) minimalna_plt, MAX(plt) maksimalna_plt
FROM radnik;

SELECT COUNT(mbr) broj_radnika, SUM(plt) ukupna_plata
FROM radnik;

SELECT COUNT(*) broj_radnika, AVG(plt) prosjecna_plata, 12*SUM(plt) godisnja_plata
FROM radnik;

SELECT SUM(pre)
FROM radnik
WHERE mbr > 100;

SELECT ROUND(AVG(plt*1.41), 2)
FROM radnik;

SELECT mbr, count(spr)
FROM radproj
WHERE mbr < 40
GROUP BY mbr
ORDER BY mbr DESC;

SELECT spr, COUNT(mbr), SUM(brc)
FROM radproj
GROUP BY spr;

SELECT mbr, COUNT(spr)
FROM radproj
GROUP BY mbr
HAVING COUNT(spr) > 2;

SELECT mbr, ime, prz, plt
FROM radnik
WHERE plt > (SELECT AVG(plt) FROM radnik)
ORDER BY plt ASC;

SELECT mbr, ime, prz
FROM radnik
WHERE mbr IN (SELECT mbr FROM radproj WHERE spr = 10) AND mbr NOT IN (SELECT mbr FROM radproj WHERE spr = 30);

SELECT ime, prz, god
FROM radnik
WHERE god = (SELECT MIN(god) FROM radnik);

SELECT p.nap
FROM projekat p
WHERE spr IN (
    SELECT spr FROM radproj
    WHERE mbr in (
    	SELECT mbr FROM radproj WHERE spr = 60
    )
);
