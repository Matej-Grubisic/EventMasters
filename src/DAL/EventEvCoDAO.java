package DAL;

import BE.Event;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventEvCoDAO implements IEventEvCoDAO {
    @Override
    public void createEventEvCo(int eventId, int coorId) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "INSERT INTO Event_EvCo (EventID, EvCoID) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, eventId);
                pstmt.setInt(2, coorId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Integer> getByEvent(int eventId){
        try (Connection con = dbConnector.getConn()) {
            ArrayList<Integer> allId = new ArrayList<>();
            String sql = "SELECT * FROM Event_EvCo WHERE EventID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, eventId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("EvCoID");
                    allId.add(id);
                }
                return allId;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delEvCo2(int evcoId){
        try (Connection con = dbConnector.getConn()) {
            String sql = "DELETE FROM Event_EvCo WHERE EvCoID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, evcoId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
