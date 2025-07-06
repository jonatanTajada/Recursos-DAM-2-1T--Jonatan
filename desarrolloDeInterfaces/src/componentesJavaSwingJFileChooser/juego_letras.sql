CREATE DATABASE juego_letras;
USE juego_letras;

CREATE TABLE puntuaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    aciertos INT NOT NULL,
    tiempo FLOAT NOT NULL
);
