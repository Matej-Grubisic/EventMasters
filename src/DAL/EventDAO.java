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
    @Override
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
    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Event";
            try (PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String time = resultSet.getString("Time");
                    String location = resultSet.getString("Location");
                    String description = resultSet.getString("Notes");
                    String name = resultSet.getString("Name");
                    events.add(new Event(id,time, description, location, name));
                }
                return events;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteEvent(Event event) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "DELETE FROM Event_EvCo WHERE EventID = ?";
            String sql1 = "DELETE FROM Ticket WHERE EventID = ?";
            String sql2 = "DELETE FROM Event WHERE ID = ?";


            try(PreparedStatement pstmt = con.prepareStatement(sql)){
                pstmt.setInt(1, event.getId());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt1 = con.prepareStatement(sql1)) {
                pstmt1.setInt(1, event.getId());
                pstmt1.executeUpdate();
            }

            try (PreparedStatement pstmt2 = con.prepareStatement(sql2)) {
                pstmt2.setInt(1, event.getId());
                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEvent(Event event) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "UPDATE Event SET Time = ?, Location = ?, Notes = ?, Name = ? WHERE ID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Set parameters for the update query
                pstmt.setString(1, event.getTime());           // Update Time
                pstmt.setString(2, event.getLocation());       // Update Location
                pstmt.setString(3, event.getDescription());    // Update Notes/Description
                pstmt.setString(4, event.getName());           // Update Name
                pstmt.setInt(5, event.getId());                 // Identify event by ID

                // Execute the update query
                int affectedRows = pstmt.executeUpdate();

                if (affectedRows == 0) {
                    // Handle if no rows were updated (event with specified ID not found)
                    System.out.println("No event found with ID: " + event.getId());
                } else {
                    System.out.println("Event updated successfully: ID - " + event.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating event: " + e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Integer> getEventID(int eventID) {
        ArrayList<Integer> eventIDs = new ArrayList<>();
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Event WHERE ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, eventID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int evID = rs.getInt("ID");
                eventIDs.add(evID);
            }
            return eventIDs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> searchEvents(String searchText) {
        List<Event> matchingEvents = new ArrayList<>();
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Event WHERE Name LIKE ? OR Time LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, "%" + searchText + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("ID"));
                event.setName(rs.getString("Name"));
                event.setTime(rs.getString("Time"));
                matchingEvents.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matchingEvents;
    }



    @Override
    public Event getEvent(int eventID) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Event WHERE ID=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, eventID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String time = rs.getString("Time");
                String location = rs.getString("Location");
                String notes = rs.getString("Notes");
                String name = rs.getString("Name");
                return new Event(time, location, notes, name);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}