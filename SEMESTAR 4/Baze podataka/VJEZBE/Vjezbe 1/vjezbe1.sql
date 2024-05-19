CREATE TABLE faze_projekta (
    Sfp integer not null,
    Spr integer not null,
	Rukfp integer,
	Nafp varchar(30),
    Datp date,
    CONSTRAINT faze_projekta_pk1 PRIMARY KEY (Sfp, Spr),
    CONSTRAINT faze_projekta_pk2 FOREIGN KEY (Spr) REFERENCES Projekat(Spr),
    CONSTRAINT faze_projekta_fk FOREIGN KEY (Rukfp) REFERENCES Radnik(Mbr),
    CONSTRAINT faze_projekta_uk UNIQUE (Nafp)
);

ALTER TABLE faze_projekta 
	ADD Datz date
	ADD CONSTRAINT date_check CHECK (Datp<=Datz);

DROP TABLE faze_projekta;

UPDATE radnik
SET plt = plt*1.2
WHERE Mbr = 210;
