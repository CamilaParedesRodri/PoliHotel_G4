package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DataHelper;
import DataAccess.IDAO;
import DataAccess.DTO.DTO_PoliReseva;
import Framework.PatException;

public class DAO_PoliReserva extends DataHelper implements IDAO<DTO_PoliReseva> {
    private static final String SELECT_COLUMNS =
            "SELECT r.IdReserva, r.idPersona, r.idHabitacion, c.Nombre, c.Apellido, "
          + "c.Telefono, h.TipoHabitacion, h.PrecioHabitacion, r.FechaIngreso, "
          + "r.FechaSalida, r.Estado "
          + "FROM PoliReserva r "
          + "JOIN PoliPersona c ON r.idPersona = c.idPersona "
          + "JOIN PoliHabitacion h ON r.idHabitacion = h.idHabitacion ";

    @Override
    public List<DTO_PoliReseva> readAll() throws Exception {
        List<DTO_PoliReseva> reservations = new ArrayList<>();
        String query = SELECT_COLUMNS + "WHERE r.Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reservations.add(mapReservation(rs));
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return reservations;
    }

    @Override
    public DTO_PoliReseva readBy(Integer id) throws Exception {
        String query = SELECT_COLUMNS + "WHERE r.Estado = 'A' AND r.IdReserva = ?";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapReservation(rs);
                }
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return new DTO_PoliReseva();
    }

    @Override
    public boolean create(DTO_PoliReseva entity) throws Exception {
        requireReferences(entity);
        if (entity.getEstado() != null && !"A".equals(entity.getEstado())) {
            throw new IllegalArgumentException("Una reserva nueva debe estar activa.");
        }
        String query = "INSERT INTO PoliReserva "
                     + "(idPersona, idHabitacion, FechaIngreso, FechaSalida, Estado) "
                     + "VALUES (?, ?, COALESCE(?, CURRENT_DATE), ?, COALESCE(?, 'A'))";
        try (Connection conn = openConnection()) {
            conn.setAutoCommit(false);
            try {
                if (!isActivePerson(conn, entity.getIdPersona())
                        || !adjustRoomAvailability(conn, entity.getIdHabitacion(), -1)) {
                    conn.rollback();
                    return false;
                }
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, entity.getIdPersona());
                    stmt.setInt(2, entity.getIdHabitacion());
                    stmt.setString(3, entity.getFechaIngreso());
                    stmt.setString(4, entity.getFechaSalida());
                    stmt.setString(5, entity.getEstado());
                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                rollback(conn, e);
                throw e;
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(DTO_PoliReseva entity) throws Exception {
        if (entity.getIdReserva() == null || entity.getIdReserva() <= 0) {
            throw new IllegalArgumentException("La reserva debe tener un IdReserva válido.");
        }
        requireReferences(entity);
        String query = "UPDATE PoliReserva SET idPersona = ?, idHabitacion = ?, "
                     + "FechaIngreso = COALESCE(?, CURRENT_DATE), FechaSalida = ?, "
                     + "Estado = COALESCE(?, 'A') WHERE IdReserva = ? AND Estado = 'A'";
        try (Connection conn = openConnection()) {
            conn.setAutoCommit(false);
            try {
                Integer currentRoomId = findActiveRoom(conn, entity.getIdReserva());
                if (currentRoomId == null) {
                    conn.rollback();
                    return false;
                }

                boolean remainsActive = entity.getEstado() == null || "A".equals(entity.getEstado());
                if (remainsActive && !isActivePerson(conn, entity.getIdPersona())) {
                    conn.rollback();
                    return false;
                }
                if (!currentRoomId.equals(entity.getIdHabitacion())) {
                    if (remainsActive && !adjustRoomAvailability(conn, entity.getIdHabitacion(), -1)) {
                        conn.rollback();
                        return false;
                    }
                    if (!adjustRoomAvailability(conn, currentRoomId, 1)) {
                        conn.rollback();
                        return false;
                    }
                } else if (!remainsActive && !adjustRoomAvailability(conn, currentRoomId, 1)) {
                    conn.rollback();
                    return false;
                }

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, entity.getIdPersona());
                    stmt.setInt(2, entity.getIdHabitacion());
                    stmt.setString(3, entity.getFechaIngreso());
                    stmt.setString(4, entity.getFechaSalida());
                    stmt.setString(5, entity.getEstado());
                    stmt.setInt(6, entity.getIdReserva());
                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                rollback(conn, e);
                throw e;
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE PoliReserva SET Estado = 'X' WHERE IdReserva = ? AND Estado = 'A'";
        try (Connection conn = openConnection()) {
            conn.setAutoCommit(false);
            try {
                Integer roomId = findActiveRoom(conn, id);
                if (roomId == null) {
                    conn.rollback();
                    return false;
                }
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, id);
                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }
                if (!adjustRoomAvailability(conn, roomId, 1)) {
                    conn.rollback();
                    return false;
                }
                conn.commit();
                return true;
            } catch (SQLException e) {
                rollback(conn, e);
                throw e;
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) FROM PoliReserva WHERE Estado = 'A'";
        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
    }

    private static DTO_PoliReseva mapReservation(ResultSet rs) throws SQLException {
        return new DTO_PoliReseva(
                rs.getInt("IdReserva"),
                rs.getInt("idPersona"),
                rs.getInt("idHabitacion"),
                rs.getString("Nombre"),
                rs.getString("Apellido"),
                rs.getString("Telefono"),
                rs.getString("TipoHabitacion"),
                rs.getBigDecimal("PrecioHabitacion"),
                rs.getString("FechaIngreso"),
                rs.getString("FechaSalida"),
                rs.getString("Estado"));
    }

    private static void requireReferences(DTO_PoliReseva entity) {
        if (entity.getIdPersona() == null || entity.getIdPersona() <= 0
                || entity.getIdHabitacion() == null || entity.getIdHabitacion() <= 0) {
            throw new IllegalArgumentException(
                    "La reserva debe incluir IdPersona e IdHabitacion válidos.");
        }
    }

    private static boolean isActivePerson(Connection conn, Integer personId) throws SQLException {
        String query = "SELECT 1 FROM PoliPersona WHERE IdPersona = ? AND Estado = 'A'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, personId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static Integer findActiveRoom(Connection conn, Integer reservationId) throws SQLException {
        String query = "SELECT idHabitacion FROM PoliReserva WHERE IdReserva = ? AND Estado = 'A'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt("idHabitacion") : null;
            }
        }
    }

    private static boolean adjustRoomAvailability(Connection conn, Integer roomId, int difference)
            throws SQLException {
        String query = difference < 0
                ? "UPDATE PoliHabitacion SET Cantidad = Cantidad + ? "
                        + "WHERE IdHabitacion = ? AND Estado = 'A' AND Cantidad > 0"
                : "UPDATE PoliHabitacion SET Cantidad = Cantidad + ? WHERE IdHabitacion = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, difference);
            stmt.setInt(2, roomId);
            return stmt.executeUpdate() > 0;
        }
    }

    private static void rollback(Connection conn, SQLException cause) {
        try {
            conn.rollback();
        } catch (SQLException rollbackError) {
            cause.addSuppressed(rollbackError);
        }
    }
}
