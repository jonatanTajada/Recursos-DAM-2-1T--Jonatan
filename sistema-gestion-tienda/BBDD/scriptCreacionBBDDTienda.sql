CREATE DATABASE tienda;

USE tienda;

-- Tabla de categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    #activo TINYINT(1) DEFAULT 1 -- '0' significa inactivo, '1' significa activo
    activo BOOLEAN DEFAULT TRUE -- 'TRUE' significa activo, 'FALSE' significa inactivo

);


-- Tabla de productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria_id INT,
    stock INT NOT NULL DEFAULT 0, -- Nueva columna stock
    cantidad INT DEFAULT 0,       -- Puedes decidir si mantener esta columna o usar solo stock
    precio DOUBLE NOT NULL,
    descripcion VARCHAR(255),
    #activo TINYINT(1) DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- Tabla de proveedores
CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contacto VARCHAR(50),
    direccion VARCHAR(255)
);

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


-- Tabla de pedidos
CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proveedor_id INT,
    id_usuario INT NOT NULL, -- Nueva columna para la relación con usuarios
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DOUBLE NOT NULL DEFAULT 0.0,
    estado VARCHAR(50) DEFAULT 'pendiente',
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);



-- Tabla de ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DOUBLE NOT NULL
);

-- Tabla de detalle de ventas
CREATE TABLE linea_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT,
    producto_id INT,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- UPDATE productos SET activo = 1;
 -- SET SQL_SAFE_UPDATES = 0;

 -- UPDATE categorias SET activo = 1 WHERE activo = 0;


