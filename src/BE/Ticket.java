package BE;

import java.util.UUID;

public class Ticket {

    private String UUID;
    private String email;

    private String type;

    private int typeID;

    private int eventId;

    public Ticket(UUID UUID, String email, int typeID) {
        this.UUID = String.valueOf(UUID);
        this.email = email;
        this.typeID = typeID;
    }

    public Ticket(UUID uuid, String email, String type) {
        this.UUID = String.valueOf(uuid);
        this.email = email;
        this.type = type;
    }

    public Ticket(java.util.UUID uuid, String email, String type, int eventId) {
        this.UUID = String.valueOf(uuid);
        this.email = email;
        this.type = type;
        this.eventId = eventId;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(UUID UUID) {
        this.UUID = String.valueOf(UUID);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getEventId(){
        return eventId;
    }

}
