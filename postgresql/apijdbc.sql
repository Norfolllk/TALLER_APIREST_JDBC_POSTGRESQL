CREATE DATABASE apijdbc; 

CREATE TABLE videojuegos(
codigo VARCHAR(10) PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
plataforma VARCHAR(50) NOT NULL,
precio decimal NOT NULL,
disponible BOOLEAN NOT NULL,
genero VARCHAR(50)
);

select * from videojuegos;