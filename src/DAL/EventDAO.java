package DAL;

import BE.Event;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventDAO{

    public void createEvent(Event event) {
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

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Event";
            try (PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    String time = resultSet.getString("Time");
                    String location = resultSet.getString("Location");
                    String description = resultSet.getString("Notes");
                    String name = resultSet.getString("Name");
                    events.add(new Event(time, description, location, name));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public void deleteEvent(Event event) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "DELETE FROM Event WHERE Name = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, event.getName());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}