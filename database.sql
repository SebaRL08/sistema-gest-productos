
CREATE DATABASE IF NOT EXISTS tienda_db;

USE tienda_db;

CREATE TABLE IF NOT EXISTS productos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL,
    CONSTRAINT chk_precio CHECK (precio >= 0),
    CONSTRAINT chk_cantidad CHECK (cantidad >= 0)
);

SHOW TABLES;

USE tienda_db;

SELECT * FROM productos;