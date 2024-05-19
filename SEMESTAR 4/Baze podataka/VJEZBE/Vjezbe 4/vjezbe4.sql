SELECT *
FROM (SELECT mbr, ime FROM radnik);

SELECT mbr, plt, ROWNUM
FROM (SELECT * FROM radnik ORDER BY plt DESC)
WHERE ROWNUM <= 10;

SELECT r.mbr, ime, prz, plt, brc
FROM radnik r, radproj p
WHERE spr = 10 AND r.mbr = p.mbr;

SELECT DISTINCT mbr, ime, prz, plt
FROM radnik, projekat
WHERE ruk = mbr;

SELECT ime, prz, COUNT(spr)
FROM radnik, projekat
WHERE ruk = mbr
GROUP BY prz, ime;

SELECT r.mbr, r.ime, r.prz, COUNT(*), SUM(rp.brc)
FROM radnik r, radproj rp
WHERE r.mbr = rp.mbr
GROUP BY r.mbr, r.prz, r.ime;

SELECT ime, prz
FROM radnik r, projekat p
WHERE NOT (p.spr = 10 AND r.mbr = p.ruk);

SELECT r.ime, r.prz, COUNT(p.spr)
FROM radnik r, radproj rp, projekat p
WHERE r.mbr = rp.mbr AND p.ruk = r.mbr
GROUP BY r.ime, r.prz;

SELECT p.nap, SUM(brc)
FROM projekat p, radproj rp
WHERE p.spr = rp.spr
GROUP BY p.nap
HAVING SUM(brc) > 15;

SELECT p.spr, p.nap, COUNT(rp.mbr)
FROM projekat p, radproj rp
WHERE p.spr = rp.spr
GROUP BY p.spr, p.nap
HAVING COUNT(rp.mbr) > 2;

SELECT p.spr, p.nap, SUM(rp.brc)
FROM projekat p, radproj rp
WHERE p.spr = rp.spr
GROUP BY p.spr, p.nap
HAVING AVG(rp.brc) > (SELECT AVG(brc) FROM radproj);

SELECT p.spr, p.nap, AVG(rp.brc)
FROM projekat p, radproj rp
WHERE p.spr = rp.spr
GROUP BY p.spr, p.nap
HAVING AVG(rp.brc) >= ALL(SELECT AVG(brc) FROM radproj GROUP BY spr); 

SELECT r.mbr, r.ime, r.prz, r.plt
FROM radnik r, radnik r1
WHERE r.plt > r1.plt AND r1.mbr = 40;

SELECT r1.ime, r1.prz, r1.plt
FROM radnik r1, radnik r2, radproj rp, projekat p
WHERE r1.mbr = rp.mbr AND rp.spr = p.spr AND p.ruk = r2.mbr AND r1.plt + 1000 < r2.plt AND r1.mbr != p.ruk;
