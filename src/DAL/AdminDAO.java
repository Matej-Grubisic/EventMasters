package dal;

import BE.Admin;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AdminDAO {

    public Admin getAdmin(){
        try (Connection connection1 = dbConnector.getConn()) {
            String sql = "SELECT 0 FROM Admin";
            Statement stmt = connection1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            HashMap<String, Integer> artists = null;
            int id = rs.getInt("ID");
            String name = rs.getString("Username");
            String password = rs.getString("Password");
            return new Admin(id, name, password);
            //return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
