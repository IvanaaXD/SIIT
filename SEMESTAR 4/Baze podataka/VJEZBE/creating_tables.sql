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