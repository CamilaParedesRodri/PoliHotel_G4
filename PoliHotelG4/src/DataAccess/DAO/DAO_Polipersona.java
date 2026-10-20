package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DataHelper;
import DataAccess.IDAO;
import DataAccess.DTO.DTO_Polipersona;
import Framework.PatException;

public class DAO_Polipersona extends DataHelper implements IDAO<DTO_Polipersona> {
    private static final String SELECT_COLUMNS =
            "SELECT IdPersona, Nombre, Apellido, Telefono, FechaRegistro, FechaModifica, Estado "
          + "FROM PoliPersona ";

    @Override
    public DTO_Polipersona readBy(Integer id) throws Exception {
        String query = SELECT_COLUMNS + "WHERE Estado = 'A' AND IdPersona = ?";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DTO_Polipersona(
                            rs.getInt("IdPersona"),
                            rs.getString("Nombre"),
                            rs.getString("Apellido"),
                            rs.getString("Telefono"),
                            rs.getString("FechaRegistro"),
                            rs.getString("FechaModifica"),
                            rs.getString("Estado"));
                }
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return new DTO_Polipersona();
    }

    @Override
    public List<DTO_Polipersona> readAll() throws Exception {
        List<DTO_Polipersona> people = new ArrayList<>();
        String query = SELECT_COLUMNS + "WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                people.add(new DTO_Polipersona(
                        rs.getInt("IdPersona"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getString("Telefono"),
                        rs.getString("FechaRegistro"),
                        rs.getString("FechaModifica"),
                        rs.getString("Estado")));
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return people;
    }

    @Override
    public boolean create(DTO_Polipersona entity) throws Exception {
        String query = "INSERT INTO PoliPersona (Nombre, Apellido, Telefono) VALUES (?, ?, ?)";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, entity.getNombre());
            stmt.setString(2, entity.getApellido());
            stmt.setString(3, entity.getTelefono());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(DTO_Polipersona entity) throws Exception {
        String query = "UPDATE PoliPersona SET Nombre = ?, Apellido = ?, Telefono = ?, "
                     + "FechaModifica = CURRENT_TIMESTAMP "
                     + "WHERE IdPersona = ? AND Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, entity.getNombre());
            stmt.setString(2, entity.getApellido());
            stmt.setString(3, entity.getTelefono());
            stmt.setInt(4, entity.getIdPersona());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE PoliPersona SET Estado = 'X' "
                     + "WHERE IdPersona = ? AND Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) FROM PoliPersona WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
    }
}
