CREATE DATABASE apijdbc;

CREATE TABLE videojuegos(
    codigo VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    precio decimal NOT NULL,
    disponible BOOLEAN NOT NULL,
    genero VARCHAR(50)
);

-- Consultar
select * from videojuegos;

-- Insertar
insert into videojuegos
(codigo, nombre, plataforma, precio, disponible, genero)
values
('PES', 'eFootball', 'steam', 0, true, null);

-- Listar
select codigo, nombre, plataforma, precio, disponible, genero from videojuegos;

-- Buscar por codigo
select codigo, nombre, plataforma, precio, disponible, genero from videojuegos
where codigo = 'PES';

-- Actualizar
update videojuegos set
nombre = 'God of War',
plataforma = 'PlayStation',
precio = 59.99,
disponible = true,
genero = 'Accion'
where codigo = 'PES';

select * from videojuegos;

-- Eliminar
delete from videojuegos
where codigo = 'PES';

select * from videojuegos;