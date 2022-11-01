DROP TABLE IF EXISTS USUARIOS;

CREATE TABLE USUARIOS
       (id INTEGER(4) AUTO_INCREMENT,
        login VARCHAR(20) UNIQUE,
        alta DATE,
        pass VARCHAR(20),
        tipo VARCHAR(10),
        PRIMARY KEY(id));

INSERT INTO USUARIOS VALUES
        (7000, 'Juan', '2019-10-10',  '123','admin');
INSERT INTO USUARIOS VALUES
        (7001, 'Alberto', '2018-11-04', '456','normal');
INSERT INTO USUARIOS VALUES
        (7002, 'Maria',  '2019-01-01', '789','normal');
INSERT INTO USUARIOS VALUES
        (7003, 'Rosa',  '2019-01-01', 'Rosa432','normal');

