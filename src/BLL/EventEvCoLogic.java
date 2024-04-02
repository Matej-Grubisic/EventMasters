package BLL;

import BE.Event;
import DAL.EventDAO;
import DAL.EventEvCoDAO;

import java.util.ArrayList;

public class EventEvCoLogic {
    EventEvCoDAO eventEvCo=new EventEvCoDAO();

    public void createEventEvCo(int eventId, int coorId){
        eventEvCo.createEventEvCo(eventId,coorId);
    }

    public ArrayList<Integer> getByEvent(int eventId){
        return eventEvCo.getByEvent(eventId);
    }
}
