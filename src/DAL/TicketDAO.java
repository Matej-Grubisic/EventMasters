package DAL;

import BE.Ticket;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TicketDAO implements ITicketDAO {
    @Override
    public void createTicket(Ticket ticket, ArrayList<Integer> eventID) {
        try (Connection con = dbConnector.getConn()) {
            String sql = "INSERT INTO Ticket (EventID, TypeID, UUID, email) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                // Iterate over the eventID ArrayList and set each value individually
                for (int i = 0; i < eventID.size(); i++) {
                    pstmt.setInt(1, eventID.get(i)); // Set the i-th value from the ArrayList
                    pstmt.setInt(2, ticket.getTypeID());
                    pstmt.setString(3, ticket.getUUID());
                    pstmt.setString(4, ticket.getEmail());
                    pstmt.addBatch(); // Add the parameters to the batch
                }
                pstmt.executeBatch(); // Execute the batch insert



            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
