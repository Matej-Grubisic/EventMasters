package DAL;

import BE.Event;

import java.util.ArrayList;
import java.util.List;

public interface IEventDAO {

     void createEvent(Event event);
    List<Event> getAllEvents();
    void deleteEvent(Event event);

    void updateEvent(Event event);
    ArrayList<Integer> getEventID(int eventID);

    Event getEvent(int eventID);
}
