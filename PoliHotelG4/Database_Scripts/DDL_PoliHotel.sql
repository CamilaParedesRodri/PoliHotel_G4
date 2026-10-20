-- database: ../Database/PoliHotel.sqlite

/* 
copyRigth Epn 2025
Autor: Anahi Pillajo 
Fecha: 13-01-2025
DDL--Data base para crear las bases principales del sistema PoliHotel. 
*/      
DROP TABLE IF EXISTS PoliReserva;
DROP TABLE IF EXISTS PoliHabitacion;
DROP TABLE IF EXISTS PoliPersona;


CREATE TABLE PoliPersona(
    idPersona INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre VARCHAR(50) NOT NULL
    ,Apellido VARCHAR(50) NOT NULL
    ,Telefono VARCHAR(10) NOT NULL
    ,FechaRegistro DATE NOT NULL DEFAULT CURRENT_DATE
    ,FechaModifica DATETIME
    ,Estado CHAR(1) NOT NULL DEFAULT 'A'
); 

CREATE TABLE PoliHabitacion(
    idHabitacion INTEGER PRIMARY KEY AUTOINCREMENT
    ,TipoHabitacion VARCHAR(50) NOT NULL
    ,PrecioHabitacion DECIMAL(10,2) NOT NULL
    ,Cantidad INTEGER NOT NULL 
    ,FechaRegistro DATE NOT NULL DEFAULT CURRENT_DATE
    ,FechaModifica DATETIME
    ,Estado CHAR(1) NOT NULL DEFAULT 'A'
);

CREATE TABLE PoliReserva(
    idReserva INTEGER PRIMARY KEY AUTOINCREMENT
    ,idPersona INTEGER NOT NULL REFERENCES PoliPersona(idPersona)
    ,idHabitacion INTEGER NOT NULL REFERENCES PoliHabitacion(idHabitacion)
    ,FechaIngreso DATE NOT NULL DEFAULT CURRENT_DATE
    ,FechaSalida DATETIME
    ,Estado CHAR(1) NOT NULL DEFAULT 'A'
);

