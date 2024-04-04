package BLL;

import BE.Ticket;
import DAL.TicketDAO;

import java.util.ArrayList;

public class TicketLogic {
    TicketDAO ticketDAO = new TicketDAO();

    public void createTicket(Ticket ticket, ArrayList<Integer> eventID) {
        ticketDAO.createTicket(ticket, eventID);
    }
}
