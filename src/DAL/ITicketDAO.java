package DAL;

import BE.Ticket;

import java.util.ArrayList;

public interface ITicketDAO {
    public void createTicket(Ticket ticket, ArrayList<Integer> eventID);
}
