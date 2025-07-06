-- Limpieza previa de tablas
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE linea_ventas;
TRUNCATE TABLE ventas;
TRUNCATE TABLE pedidos;
TRUNCATE TABLE productos;
TRUNCATE TABLE usuarios;
TRUNCATE TABLE categorias;
TRUNCATE TABLE proveedores;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertar datos en la tabla de categorías con 'activo' igual a true
INSERT INTO categorias (id, nombre, descripcion, activo) VALUES
(1, 'Electrónica', 'Artículos electrónicos como smartphones, laptops, etc.', true),
(2, 'Hogar', 'Productos para el hogar y decoración.', true),
(3, 'Deportes', 'Equipos y ropa deportiva.', true),
(4, 'Ropa', 'Prendas de vestir para todas las edades.', true),
(5, 'Alimentos', 'Productos alimenticios y bebidas.', true);

-- 2. Insertar datos en la tabla de usuarios
INSERT INTO usuarios (id, username, password) VALUES
(1, 'admin', 'admin123'),
(2, 'empleado1', 'empleado123'),
(3, 'empleado2', 'empleado123');

-- 3. Insertar datos en la tabla de proveedores
INSERT INTO proveedores (id, nombre, contacto, direccion) VALUES
(1, 'Proveedor A', '111111111', 'Calle Primera 123'),
(2, 'Proveedor B', '222222222', 'Calle Segunda 456');

-- 4. Insertar datos en la tabla de productos con 'activo' igual a true
INSERT INTO productos (id, nombre, categoria_id, stock, precio, descripcion, activo) VALUES
(1, 'Smartphone', 1, 50, 599.99, 'Un teléfono inteligente de última generación.', true),
(2, 'Laptop', 1, 30, 999.99, 'Computadora portátil con alta capacidad.', true),
(3, 'Silla', 2, 100, 49.99, 'Silla ergonómica para oficina.', true),
(4, 'Mesa', 2, 40, 89.99, 'Mesa de comedor para 6 personas.', true),
(5, 'Balón de fútbol', 3, 200, 19.99, 'Balón oficial de alta calidad.', true),
(6, 'Camiseta deportiva', 3, 150, 29.99, 'Camiseta para practicar deportes.', true),
(7, 'Pantalón vaquero', 4, 80, 39.99, 'Pantalón de mezclilla clásico.', true),
(8, 'Zapatillas deportivas', 4, 70, 59.99, 'Zapatillas cómodas para correr.', true),
(9, 'Manzanas', 5, 500, 1.99, 'Manzanas frescas por kilogramo.', true),
(10, 'Leche', 5, 300, 0.99, 'Leche de vaca pasteurizada.', true);

-- 5. Insertar datos en la tabla de pedidos
INSERT INTO pedidos (id, proveedor_id, id_usuario, fecha, total, estado) VALUES
(1, 1, 2, '2024-11-01', 120.50, 'pendiente'),
(2, 2, 3, '2024-11-02', 89.99, 'pendiente'),
(3, 1, 2, '2024-11-03', 199.99, 'entregado');

-- 6. Insertar datos en la tabla de ventas
INSERT INTO ventas (id, fecha, total) VALUES
(1, '2024-11-05 10:30:00', 500.00),
(2, '2024-11-06 15:45:00', 300.00),
(3, '2024-11-07 12:20:00', 150.00);

-- 7. Insertar datos en la tabla de detalle de ventas
INSERT INTO linea_ventas (id, venta_id, producto_id, cantidad, subtotal) VALUES
(1, 1, 1, 1, 599.99),
(2, 1, 9, 5, 9.95),
(3, 2, 3, 3, 149.97),
(4, 2, 4, 2, 179.98),
(5, 3, 5, 10, 199.90);

-- Fin del script





-- Verificar las relaciones (opcional)
SELECT * FROM categorias;
SELECT * FROM productos;
SELECT * FROM usuarios;
SELECT * FROM pedidos;
SELECT * FROM proveedores;



-- Trigger para caluclar el subtotal
DELIMITER $$

CREATE TRIGGER before_insert_linea_ventas
BEFORE INSERT ON linea_ventas
FOR EACH ROW
BEGIN
    -- Calcular el subtotal: precio * cantidad
    DECLARE precio_producto DOUBLE;
    SELECT precio INTO precio_producto FROM productos WHERE id = NEW.producto_id;

    SET NEW.subtotal = precio_producto * NEW.cantidad;
END$$

DELIMITER ;

