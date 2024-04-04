package BE;
import java.util.UUID;
public class Ticket {

    private String UUID;
    private String email;

    private int typeID;

    public Ticket(UUID UUID, String email, int typeID) {
        this.UUID = String.valueOf(UUID);
        this.email = email;
        this.typeID = typeID;
    }

    public Ticket() {

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

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
