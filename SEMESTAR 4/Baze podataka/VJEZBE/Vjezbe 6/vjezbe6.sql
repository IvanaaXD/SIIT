SELECT S.id_sc,S.name_sc, s.numofseats_sc, s.theatre_id_th 
FROM Scene S,  Scene S1, Theatre T1
WHERE s.numofseats_sc >= s1.numofseats_sc*0.8 AND
s.numofseats_sc <= s1.numofseats_sc*1.2 AND s1.name_sc = 'Scena Joakim Vujic' AND s1.theatre_id_th = t1.id_th
AND t1.name_th = 'Knjazevsko-srpski teatar Kragujevac';

select play_id_pl, sum(numofspec_sh) from showing  where scene_id_sc=10  group by play_id_pl;

select count(*) from role where play_id_pl=20;

SELECT p.id_pl ,name_pl, AVG(s.numofspec_sh)
FROM play p, showing s 
WHERE p.id_pl = s.play_id_pl 
GROUP BY p.id_pl, name_pl 
HAVING AVG(s.numofspec_sh) >= ALL(SELECT AVG(numofspec_sh) FROM showing GROUP BY play_id_pl);


select ordnum_sh,date_sh,time_sh,numofspec_sh,play_id_pl,scene_id_sc from showing where play_id_pl = 10;

select ID_AC, NAME_AC, DOB_AC, STATUS_AC, SALARY_AC, BONUS_AC, THEATRE_ID_TH  from actor;

SELECT id_ac, name_ac, salary_ac
FROM actor
WHERE theatre_id_th IN (
    SELECT t.id_th
    FROM showing s, scene sc, theatre t
    WHERE s.scene_id_sc = sc.id_sc AND sc.theatre_id_th = t.id_th AND s.date_sh < sysdate 
        AND s.play_id_pl IN (SELECT play_id_pl
        FROM role 
        WHERE id_ro NOT IN (SELECT DISTINCT role_id_ro FROM assignment))
    GROUP BY t.id_th
    ORDER BY t.id_th ASC );