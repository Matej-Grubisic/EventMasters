package DAL;

import BE.Event;
import BE.Ticket;
import BLL.Notifications;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDAO implements ITicketDAO {
    private Notifications nt = new Notifications();

    @Override
    public void createTicket(Ticket ticket, ArrayList<Integer> eventID) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "INSERT INTO Ticket (EventID, TypeID, UUID, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Iterate over the eventID ArrayList and set each value individually
                for (int i = 0; i < eventID.size(); i++) {
                    pstmt.setInt(1, eventID.get(i)); // Set the i-th value from the ArrayList
                    pstmt.setInt(2, ticket.getTypeID());
                    pstmt.setString(3, String.valueOf(ticket.getUUID()));
                    pstmt.setString(4, ticket.getEmail());
                    pstmt.addBatch(); // Add the parameters to the batch
                }
                pstmt.executeBatch(); // Execute the batch insert
            }
        } catch (SQLException e) {
            nt.showError("System error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (Connection con = dbConnector.getConn()) {
            String sql = "SELECT * FROM Ticket";
            try (PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int eventId = resultSet.getInt("EventID");
                    int typeId = resultSet.getInt("TypeID");
                    String type = "";
                    switch(typeId) {
                        case 0:
                            type = "Free drinks";
                            break;
                        case 1:
                            type = "Regular";
                            break;
                        case 2:
                            type = "VIP";
                            break;
                        default:
                            break;
                    }
                    UUID uuid = UUID.fromString(resultSet.getString("UUID"));
                    String email = resultSet.getString("email");
                    tickets.add(new Ticket(uuid, email, type, eventId));
                }
                return tickets;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
