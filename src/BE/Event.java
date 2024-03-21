package BE;

import java.awt.*;

public class Event {
    private String name;
    private String location;
    private double time;

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getTime() {
        return time;
    }
}
