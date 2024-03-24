package DAL;

import BE.Event;

import java.util.List;

public interface IEventDAO {

     void createEvent(Event event);
    List<Event> getAllEvents();
}
