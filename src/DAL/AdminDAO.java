package DAL;

import BE.Admin;
import BLL.dbConnector;
import DAL.IAdminDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AdminDAO implements IAdminDAO {

    public Admin getAdmin(){
        try (Connection connection1 = dbConnector.getConn()) {
            String sql = "SELECT * FROM Admin";
            Statement stmt = connection1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Admin admin1 = null;
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Username");
                String password = rs.getString("Password");
                admin1 = new Admin(id, name, password);
            }
            return admin1;
            //return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
