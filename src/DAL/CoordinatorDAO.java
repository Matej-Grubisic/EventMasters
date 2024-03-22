package DAL;

import BE.Admin;
import BE.Coordinator;
import BLL.dbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class CoordinatorDAO {

    public Coordinator getCoordinator(){
        try (Connection connection1 = dbConnector.getConn()) {
            String sql = "SELECT * FROM EvCo";
            Statement stmt = connection1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Coordinator coordinator1 = null;
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Username");
                String password = rs.getString("Password");
                coordinator1 = new Coordinator(id, name, password);
            }
            return coordinator1;
            //return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
