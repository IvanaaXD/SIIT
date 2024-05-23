DROP TABLE actor CASCADE CONSTRAINTS;

DROP TABLE assignment CASCADE CONSTRAINTS;

DROP TABLE country CASCADE CONSTRAINTS;

DROP TABLE department CASCADE CONSTRAINTS;

DROP TABLE employee CASCADE CONSTRAINTS;

DROP TABLE role CASCADE CONSTRAINTS;

DROP TABLE place CASCADE CONSTRAINTS;

DROP TABLE play CASCADE CONSTRAINTS;

DROP TABLE scene CASCADE CONSTRAINTS;

DROP TABLE showing CASCADE CONSTRAINTS;

DROP TABLE technician CASCADE CONSTRAINTS;

DROP TABLE theatre CASCADE CONSTRAINTS;

DROP TABLE theatremanager CASCADE CONSTRAINTS;

CREATE TABLE actor (
    id_ac           INTEGER NOT NULL,
    name_ac         VARCHAR2(32 CHAR) NOT NULL,
    dob_ac          DATE,
    status_ac       CHAR(1),
    salary_ac       NUMBER(10, 2) NOT NULL,
    bonus_ac        NUMBER(10, 2),
    theatre_id_th   INTEGER
);

ALTER TABLE actor ADD CONSTRAINT actor_pk PRIMARY KEY ( id_ac );

CREATE TABLE assignment (
    id_as          INTEGER NOT NULL,
    honorar_as     NUMBER(10, 2) NOT NULL,
    startdate_as   DATE NOT NULL,
    enddate_as     DATE NOT NULL,
    actor_id_ac    INTEGER NOT NULL,
    role_id_ro     INTEGER NOT NULL
);

ALTER TABLE assignment ADD CONSTRAINT assignment_pk PRIMARY KEY ( id_as );

CREATE TABLE country (
    id_cn     INTEGER NOT NULL,
    name_cn   VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE country ADD CONSTRAINT country_pk PRIMARY KEY ( id_cn );

CREATE TABLE department (
    id_dpt          INTEGER NOT NULL,
    name_dp         VARCHAR2(32 CHAR) NOT NULL,
    theatre_id_th   INTEGER NOT NULL
);

ALTER TABLE department ADD CONSTRAINT department_pk PRIMARY KEY ( id_dpt );

CREATE TABLE employee (
    id_em               INTEGER NOT NULL,
    name_em             VARCHAR2(32 CHAR) NOT NULL,
    surname_em          VARCHAR2(32 CHAR) NOT NULL,
    salary_em           NUMBER(10, 2) NOT NULL,
    type_em             VARCHAR2(30 CHAR) NOT NULL,
    department_id_dpt   INTEGER NOT NULL
);

ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY ( id_em );

CREATE TABLE role (
    id_ro        INTEGER NOT NULL,
    name_ro      VARCHAR2(64 CHAR) NOT NULL,
    gender_ro    CHAR(1 CHAR) NOT NULL,
    type_ro      VARCHAR2(32 CHAR) NOT NULL,
    play_id_pl   INTEGER NOT NULL
);

ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id_ro );

CREATE TABLE place (
    id_pl           INTEGER NOT NULL,
    name_pl         VARCHAR2(32 CHAR) NOT NULL,
    country_id_cn   INTEGER NOT NULL
);

ALTER TABLE place ADD CONSTRAINT place_pk PRIMARY KEY ( id_pl );

CREATE TABLE play (
    id_pl         INTEGER NOT NULL,
    name_pl       VARCHAR2(32 CHAR) NOT NULL,
    duration_pl   VARCHAR2(32 CHAR) NOT NULL,
    year_pl       INTEGER NOT NULL
);

ALTER TABLE play ADD CONSTRAINT play_pk PRIMARY KEY ( id_pl );

CREATE TABLE scene (
    id_sc           INTEGER NOT NULL,
    name_sc         VARCHAR2(32 CHAR) NOT NULL,
    numofseats_sc   INTEGER NOT NULL,
    theatre_id_th   INTEGER NOT NULL
);

ALTER TABLE scene ADD CONSTRAINT scene_pk PRIMARY KEY ( id_sc );

CREATE TABLE showing (
    ordnum_sh      INTEGER NOT NULL,
    date_sh        DATE NOT NULL,
    time_sh        DATE NOT NULL,
    numofspec_sh   INTEGER NOT NULL,
    play_id_pl     INTEGER NOT NULL,
    scene_id_sc    INTEGER NOT NULL
);

ALTER TABLE showing ADD CONSTRAINT showing_pk PRIMARY KEY ( ordnum_sh );

CREATE TABLE technician (
    id_em       INTEGER NOT NULL,
    aptype_tc   VARCHAR2(32 CHAR) NOT NULL
);

ALTER TABLE technician ADD CONSTRAINT technician_pk PRIMARY KEY ( id_em );

CREATE TABLE theatre (
    id_th         INTEGER NOT NULL,
    name_th       VARCHAR2(64 CHAR) NOT NULL,
    address_th    VARCHAR2(64 CHAR) NOT NULL,
    website_th    VARCHAR2(64 CHAR),
    place_id_pl   INTEGER NOT NULL
);

ALTER TABLE theatre ADD CONSTRAINT theatre_pk PRIMARY KEY ( id_th );

CREATE TABLE theatremanager (
    id_em         INTEGER NOT NULL,
    profqual_tm   INTEGER NOT NULL
);

ALTER TABLE theatremanager ADD CONSTRAINT theatremanager_pk PRIMARY KEY ( id_em );

ALTER TABLE actor
    ADD CONSTRAINT actor_theatre_fk FOREIGN KEY ( theatre_id_th )
        REFERENCES theatre ( id_th );

ALTER TABLE assignment
    ADD CONSTRAINT assignment_actor_fk FOREIGN KEY ( actor_id_ac )
        REFERENCES actor ( id_ac );

ALTER TABLE assignment
    ADD CONSTRAINT assignment_role_fk FOREIGN KEY ( role_id_ro )
        REFERENCES role ( id_ro );

ALTER TABLE department
    ADD CONSTRAINT department_theatre_fk FOREIGN KEY ( theatre_id_th )
        REFERENCES theatre ( id_th );

ALTER TABLE employee
    ADD CONSTRAINT employee_department_fk FOREIGN KEY ( department_id_dpt )
        REFERENCES department ( id_dpt );

ALTER TABLE role
    ADD CONSTRAINT role_play_fk FOREIGN KEY ( play_id_pl )
        REFERENCES play ( id_pl );

ALTER TABLE place
    ADD CONSTRAINT place_country_fk FOREIGN KEY ( country_id_cn )
        REFERENCES country ( id_cn );

ALTER TABLE scene
    ADD CONSTRAINT scene_theatre_fk FOREIGN KEY ( theatre_id_th )
        REFERENCES theatre ( id_th );

ALTER TABLE showing
    ADD CONSTRAINT showing_play_fk FOREIGN KEY ( play_id_pl )
        REFERENCES play ( id_pl );

ALTER TABLE showing
    ADD CONSTRAINT showing_scene_fk FOREIGN KEY ( scene_id_sc )
        REFERENCES scene ( id_sc );

ALTER TABLE technician
    ADD CONSTRAINT technician_employee_fk FOREIGN KEY ( id_em )
        REFERENCES employee ( id_em );

ALTER TABLE theatre
    ADD CONSTRAINT theatre_place_fk FOREIGN KEY ( place_id_pl )
        REFERENCES place ( id_pl );

ALTER TABLE theatremanager
    ADD CONSTRAINT theatremanager_employee_fk FOREIGN KEY ( id_em )
        REFERENCES employee ( id_em );