SELECT * FROM radnik;

SELECT * FROM projekat;

SELECT * FROM radproj;

SELECT ime, prz
FROM radnik;

SELECT DISTINCT ime
FROM radnik;

SELECT DISTINCT ime, prz
FROM radnik;

SELECT mbr, ime, prz
FROM radnik
WHERE plt>25000;

SELECT mbr, ime, prz, plt, plt*12
FROM radnik;

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NULL;

SELECT mbr, ime, prz
FROM radnik
WHERE sef IS NOT NULL;

SELECT mbr, ime, prz
FROM radnik
WHERE plt BETWEEN 20000 and 24000;

SELECT ime, prz, god
FROM radnik
WHERE god NOT BETWEEN '01-JAN-1973' AND '31-DEC-1980';

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE 'M%';

SELECT mbr, ime, prz
FROM radnik
WHERE ime LIKE '_a%';

SELECT DISTINCT ime
FROM radnik
WHERE ime LIKE 'E%';

SELECT mbr, ime, prz
FROM radnik
WHERE prz LIKE '%E%' OR prz LIKE '%e%';

SELECT mbr, ime, prz
FROM radnik
WHERE ime NOT LIKE 'A%';

SELECT DISTINCT mbr
FROM radproj
WHERE spr IN (10, 20, 30);

SELECT DISTINCT mbr
FROM radproj
WHERE brc IN (2, 4, 6) OR spr = '10';

SELECT mbr, ime, prz
FROM radnik
WHERE ime not in ('Ana', 'Sanja');