create table akcija (
    ida int primary key,
    naziva varchar(50) not null,
    popust decimal(10,2) not null,
    brkomp int default 0
);

create table racunar (
    idr int primary key,
    nazivr varchar(20) not null
);

create table komponenta (
    idk int primary key,
    nazivk varchar(20) not null,
    tip varchar (15) not null check (tip in ('maticna', 'RAM', 'CPU', 'GPU', 'SSD', 'napajanje', 'hladnjak')),
    proizvodjac varchar(20) not null,
    cena number(10,2),
    akc int references akcija
);

create table konfiguracija (
    idr int references racunar,
    idk int references komponenta,
	komada int not null,
    constraint konf_pk primary key (idr, idk)
);