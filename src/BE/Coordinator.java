package BE;

import java.util.Arrays;

public class Coordinator {
    int id;
    String username;
    String password;


    public Coordinator(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Coordinator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

}
