# PoliHotelG4

Aplicación Java con persistencia SQLite para gestionar personas, habitaciones y reservas.

## Requisitos

- JDK instalado y `java`/`javac` disponibles en el `PATH`.
- Las dependencias incluidas en `lib`.
- La base de datos existente en `Database/PoliHotel.sqlite`.

## Ejecutar desde la carpeta `PoliHotelG4`

En Windows PowerShell:

```powershell
javac -encoding UTF-8 -cp "lib\*" -d bin (Get-ChildItem src -Recurse -Filter *.java).FullName
java -cp "bin;lib\*" App
```

La aplicación usa `Database/PoliHotel.sqlite` de forma predeterminada. Para seleccionar otra base de datos, define la propiedad JVM `polihotel.db` con su ruta:

```powershell
java -Dpolihotel.db="C:\ruta\PoliHotel.sqlite" -cp "bin;lib\*" App
```
