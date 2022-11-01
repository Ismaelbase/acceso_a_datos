DROP TABLE IF EXISTS INSCRIPCIONES;    
DROP TABLE IF EXISTS EVENTOS;
DROP TABLE IF EXISTS SOCIOS;
  

    CREATE TABLE SOCIOS
           (
            id INTEGER AUTO_INCREMENT,
            nombre VARCHAR(50) UNIQUE,
            alta DATE,
            PRIMARY KEY(id)
           );

    CREATE TABLE EVENTOS
           (
            id INTEGER AUTO_INCREMENT,
            nombre VARCHAR(50) UNIQUE,
            fecha DATE,
            PRIMARY KEY(id)
           );

    CREATE TABLE INSCRIPCIONES
          (
            id INTEGER AUTO_INCREMENT,
            socio INTEGER,
            evento INTEGER,
            FOREIGN KEY (socio) REFERENCES SOCIOS(id),
            FOREIGN KEY (evento) REFERENCES EVENTOS(id),
            PRIMARY KEY(id)
           );

    INSERT INTO SOCIOS VALUES
            (7000, 'Juan', '2010-10-05'),
            (7001, 'Antonio', '2014-11-15'),
            (7002, 'Jose', '2016-01-09');
    INSERT INTO EVENTOS VALUES
            (8888, 'Fiesta de la espuma', '2020-12-20'),
            (8889, 'Cata de vinos', '2020-11-30'),
            (8890, 'Maraton de cine', '2020-05-30'),
            (8891, 'Fiesta COVID-19', '2020-06-30');
    INSERT INTO INSCRIPCIONES VALUES
            (1,7000,8888),
            (2,7000,8889),
            (3,7001,8888);

