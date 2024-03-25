package BLL;

import BE.Event;
import DAL.EventDAO;

import java.sql.SQLException;
import java.util.List;

//The class that will pass db methods for controller.
public class EventLogic {
    EventDAO eventDAO=new EventDAO();

    public void createEvent(Event event){
        eventDAO.createEvent(event);
    }
    public List<Event> getAllEvents() throws SQLException {
        return eventDAO.getAllEvents();
    }
    public void deleteEvent(Event event) {
        eventDAO.deleteEvent(event);
    }
}
