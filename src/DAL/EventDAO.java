package DAL;

import BE.Event;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDAO {

    public static void createEvent(Event event) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "INSERT INTO Event (Time, Location, Notes, Name) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, event.getTime());
                pstmt.setString(2, event.getLocation());
                pstmt.setString(3, event.getDescription());
                pstmt.setString(4, event.getName());

                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}