package BE;

import java.awt.*;

public class Event {
    private String name;
    private String location;
    private String time;
    private String description;

    public Event(String time, String description, String location, String name) {
        this.time=time;
        this.description=description;
        this.location=location;
        this.name=name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }
}
