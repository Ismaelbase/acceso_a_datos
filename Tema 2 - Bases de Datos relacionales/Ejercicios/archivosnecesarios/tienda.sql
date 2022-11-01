DROP TABLE IF EXISTS VENTAS;
DROP TABLE IF EXISTS PRODUCTOS;
DROP TABLE IF EXISTS CLIENTES;    
  

    CREATE TABLE CLIENTES
           (
            id INTEGER AUTO_INCREMENT,
            nombre VARCHAR(50) UNIQUE,
            direccion VARCHAR(50),
            PRIMARY KEY(id)
           );

    CREATE TABLE PRODUCTOS
           (
            id INTEGER AUTO_INCREMENT,
            nombre VARCHAR(50) UNIQUE,
            precio DOUBLE,
            PRIMARY KEY(id)
           );

    CREATE TABLE VENTAS
          (
            id INTEGER AUTO_INCREMENT,
            producto INTEGER,
            cliente INTEGER,
            fecha DATE,
            unidades INT(3),
            FOREIGN KEY (cliente) REFERENCES CLIENTES(id),
            FOREIGN KEY (producto) REFERENCES PRODUCTOS(id),
            PRIMARY KEY(id)
           );
   INSERT INTO CLIENTES VALUES
    (7000,'Paco Menendez', 'Plza. Rodrigo Botet, 5'),
    (7001,'Pepe Luis Jimenez','Av. del Puerto, 214'),
    (7004,'Francisco López Serrano', 'C/Cuenca, 68');

          
    INSERT INTO PRODUCTOS VALUES
        (8000,'Plato de macarrones',10),
        (8001,'Botella de agua',2),
        (8002,'Botella de vino',20),
        (8003,'Barra de pan',1);

    INSERT INTO VENTAS VALUES
            (1,8000,7000,'2020-10-11',5),
            (2,8001,7004,'2020-12-17',7),
            (3,8002,7000,'2020-08-02',4),
            (4,8002,7001,'2000-09-09',3),
            (5,8001,7001,'2019-10-14',10);

