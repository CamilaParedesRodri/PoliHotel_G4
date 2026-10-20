package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class DataHelper {
    private static final String DATABASE_PROPERTY = "polihotel.db";
    private static final String DEFAULT_DATABASE_PATH = "Database/PoliHotel.sqlite";
    
    protected DataHelper(){}
    protected static Connection openConnection() throws SQLException {
        String configuredPath = System.getProperty(DATABASE_PROPERTY, DEFAULT_DATABASE_PATH);
        Path databasePath = Paths.get(configuredPath).toAbsolutePath().normalize();
        if (!Files.isRegularFile(databasePath)) {
            throw new SQLException("No se encontró la base de datos: " + databasePath);
        } 
        String sqlitePath = databasePath.toString().replace('\\', '/');
        return DriverManager.getConnection("jdbc:sqlite:" + sqlitePath);
    }
}
