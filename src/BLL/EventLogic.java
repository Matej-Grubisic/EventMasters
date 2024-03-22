package BLL;

import BE.Event;
import DAL.EventDAO;

//The class that will pass db methods for controller.
public class EventLogic {
    EventDAO eventDAO=new EventDAO();

    public void createEvent(Event event){
        eventDAO.createEvent(event);
    }
}
