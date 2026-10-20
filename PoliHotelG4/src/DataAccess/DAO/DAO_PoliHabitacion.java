package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DataHelper;
import DataAccess.IDAO;
import DataAccess.DTO.DTO_PoliHabitacion;
import Framework.PatException;

public class DAO_PoliHabitacion extends DataHelper implements IDAO<DTO_PoliHabitacion> {
    private static final String SELECT_COLUMNS =
            "SELECT IdHabitacion, TipoHabitacion, PrecioHabitacion, Cantidad, "
          + "FechaRegistro, FechaModifica, Estado FROM PoliHabitacion ";

    @Override
    public DTO_PoliHabitacion readBy(Integer id) throws Exception {
        String query = SELECT_COLUMNS + "WHERE Estado = 'A' AND IdHabitacion = ?";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DTO_PoliHabitacion(
                            rs.getInt("IdHabitacion"),
                            rs.getString("TipoHabitacion"),
                            rs.getBigDecimal("PrecioHabitacion"),
                            rs.getInt("Cantidad"),
                            rs.getString("FechaRegistro"),
                            rs.getString("FechaModifica"),
                            rs.getString("Estado"));
                }
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return new DTO_PoliHabitacion();
    }

    @Override
    public List<DTO_PoliHabitacion> readAll() throws Exception {
        List<DTO_PoliHabitacion> rooms = new ArrayList<>();
        String query = SELECT_COLUMNS + "WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                rooms.add(new DTO_PoliHabitacion(
                        rs.getInt("IdHabitacion"),
                        rs.getString("TipoHabitacion"),
                        rs.getBigDecimal("PrecioHabitacion"),
                        rs.getInt("Cantidad"),
                        rs.getString("FechaRegistro"),
                        rs.getString("FechaModifica"),
                        rs.getString("Estado")));
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return rooms;
    }

    @Override
    public boolean create(DTO_PoliHabitacion entity) throws Exception {
        String query = "INSERT INTO PoliHabitacion (TipoHabitacion, PrecioHabitacion, Cantidad) "
                     + "VALUES (?, ?, ?)";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, entity.getTipoHabitacion());
            stmt.setBigDecimal(2, entity.getPrecioHabitacion());
            stmt.setInt(3, entity.getCantidad());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(DTO_PoliHabitacion entity) throws Exception {
        String query = "UPDATE PoliHabitacion SET TipoHabitacion = ?, PrecioHabitacion = ?, "
                     + "Cantidad = ?, FechaModifica = CURRENT_TIMESTAMP "
                     + "WHERE IdHabitacion = ? AND Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, entity.getTipoHabitacion());
            stmt.setBigDecimal(2, entity.getPrecioHabitacion());
            stmt.setInt(3, entity.getCantidad());
            stmt.setInt(4, entity.getIdHabitacion());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE PoliHabitacion SET Estado = 'X' "
                     + "WHERE IdHabitacion = ? AND Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) FROM PoliHabitacion WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
    }
}
