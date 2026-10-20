-- database: ../Database/PoliHotel.sqlite
/* 
copyRigth Epn 2025
Autor: Anahi Pillajo 
Fecha: 13-01-2025
DDL--Data base para realizar las operaciones del PoliHotel. 

*/


INSERT INTO PoliPersona (Nombre, Apellido, Telefono) VALUES
('Ana', 'Gómez', '0987654339'),
('Pedro', 'López', '0987654340'),
('Laura', 'Jiménez', '0987654341'),
('Miguel', 'Ortiz', '0987654342'),
('Carmen', 'Vargas', '0987654343'),
('Juan', 'Castro', '0987654344'),
('Patricia', 'Molina', '0987654345'),
('Roberto', 'Suárez', '0987654346'),
('Sandra', 'Morales', '0987654347'),
('Fernando', 'García', '0987654348'),
('Isabel', 'Ramos', '0987654349'),
('Alberto', 'Paredes', '0987654350'),
('Gloria', 'Navarro', '0987654351'),
('Héctor', 'Vera', '0987654352'),
('Rosa', 'Cabrera', '0987654353'),
('Francisco', 'Espinoza', '0987654354'),
('Teresa', 'Mendoza', '0987654355'),
('Jorge', 'Soto', '0987654356'),
('Marta', 'Romero', '0987654357'),
('Raúl', 'Vargas', '0987654358'),
('Alicia', 'Herrera', '0987654359'),
('Enrique', 'Ríos', '0987654360'),
('Silvia', 'Lara', '0987654361'),
('Mario', 'Cruz', '0987654362'),
('Beatriz', 'Guzmán', '0987654363'),
('Luis', 'Méndez', '0987654364'),
('Claudia', 'Paz', '0987654365'),
('Oscar', 'Rojas', '0987654366'),
('Elena', 'Serrano', '0987654367'),
('Carlos', 'Vega', '0987654368'),
('Lucía', 'Campos', '0987654369'),
('Diego', 'Silva', '0987654370'),
('Sofía', 'López', '0987654371'),
('Andrés', 'Gómez', '0987654372'),
('María', 'Díaz', '0987654373'),
('Javier', 'Pérez', '0987654374'),
('Elena', 'García', '0987654375'),
('Daniel', 'Martínez', '0987654376'),
('Carolina', 'Hernández', '0987654377'),
('Luis', 'Ramírez', '0987654378'),
('Paola', 'Torres', '0987654379'),
('José', 'González', '0987654380'),
('Valeria', 'Sánchez', '0987654381'),
('Ricardo', 'Flores', '0987654382'),
('Mónica', 'Jiménez', '0987654383'),
('Gabriel', 'Moreno', '0987654384'),
('Ana', 'Ruiz', '0987654385'),
('Pedro', 'Ortiz', '0987654386'),
('Ana', 'Pillajo', '0987654300'),
('Laura', 'Vargas', '0987654387');

INSERT INTO PoliHabitacion (TipoHabitacion, PrecioHabitacion, Cantidad) VALUES
('Simple', 50.00, 17),
('Doble', 80.00, 15),
('Triple', 100.00, 15),
('Suite', 150.00, 15),
('Presidencial', 200.00, 11);



INSERT INTO PoliReserva (idPersona, idHabitacion) VALUES
(3, 1),
(12, 2),
(8, 3),
(22, 4),
(19, 5),
(7, 1),
(15, 2),
(26, 3),
(30, 4),
(5, 5);

UPDATE PoliHabitacion
SET Cantidad = Cantidad - 2
WHERE idHabitacion IN (1, 2, 3, 4, 5);

INSERT INTO PoliReserva (idPersona, idHabitacion) VALUES
(20, 2),
(14, 3),
(9, 4),
(35, 5),
(17, 1),
(27, 2),
(33, 3),
(21, 4),
(11, 5),
(18, 1);

UPDATE PoliHabitacion
SET Cantidad = Cantidad - 2
WHERE idHabitacion IN (1, 2, 3, 4, 5);

INSERT INTO PoliReserva (idPersona, idHabitacion) VALUES
(23, 3),
(29, 4),
(31, 5),
(13, 1),
(24, 2),
(16, 3),
(28, 4),
(34, 5),
(10, 1),
(36, 2);

UPDATE PoliHabitacion
SET Cantidad = Cantidad - 2
WHERE idHabitacion IN (1, 2, 3, 4, 5);

INSERT INTO PoliReserva (idPersona, idHabitacion) VALUES
(37, 3),
(38, 4),
(39, 5),
(40, 1),
(41, 2),
(42, 3),
(43, 4),
(44, 5),
(45, 1),
(46, 2);

UPDATE PoliHabitacion
SET Cantidad = Cantidad - 2
WHERE idHabitacion IN (1, 2, 3, 4, 5);

INSERT INTO PoliReserva (idPersona, idHabitacion) VALUES
(47, 3),
(48, 4),
(49, 5),
(50, 1),
(4, 2),
(5, 3),
(5, 4),
(6, 5),
(2, 1),
(1, 2);

UPDATE PoliHabitacion
SET Cantidad = Cantidad - 2
WHERE idHabitacion IN (1,2,3,4,5);

SELECT 
       r.IdReserva      CODIGO_RESERVA
       ,c.Nombre        NOMBRE_CLIENTE
       ,C.Apellido      APELLIDO_CLIENTE
       ,c.Telefono      TELEFONO_CLIENTE
       ,h.TipoHabitacion TIPO_HABITACION
       ,h.PrecioHabitacion PRECIO_HABITACION
       ,r.FechaIngreso   FECHA_RESERVA
       ,r.FechaSalida    FECHA_SALIDA
       ,r.Estado         ESTADO_RESERVA

FROM PoliReserva r
JOIN PoliPersona c on r.idPersona= c.idPersona
JOIN PoliHabitacion h on r.idHabitacion = h.idHabitacion;

