CREATE DATABASE empresa_Orduna;
USE empresa_Ordunia;

CREATE TABLE IF NOT exists departamento(
	 id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT exists Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    genero TINYINT(1) NOT NULL -- 1 para masculino, 0 para femenino
   -- departamento_id INT,
   -- FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);


-- 1. Añadir la columna departamento_id a la tabla Empleado
ALTER TABLE Empleado 
ADD COLUMN departamento_id INT;

-- 2. Crear la clave foránea para relacionar Empleado con Departamento
ALTER TABLE Empleado 
ADD CONSTRAINT fk_departamento
FOREIGN KEY (departamento_id) REFERENCES Departamento(id);
