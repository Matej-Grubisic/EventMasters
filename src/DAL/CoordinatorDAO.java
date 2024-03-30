package DAL;

import BE.Coordinator;
import BLL.dbConnector;

import java.sql.*;
import java.util.ArrayList;

public class CoordinatorDAO implements ICoordinatorDAO {

    @Override
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
                coordinator1 = new Coordinator(name, id);
            }
            return coordinator1;
            //return persons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<Coordinator> getCoordinatorAll(){
        try (Connection connection1 = dbConnector.getConn()) {
            String sql = "SELECT * FROM EvCo";
            Statement stmt = connection1.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Coordinator coordinator1 = null;
            ArrayList<Coordinator> coordinators = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Username");
                coordinator1 = new Coordinator(name, id);
                coordinators.add(coordinator1);
            }
            return coordinators;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void createCoordinator(Coordinator c){
        try(Connection connection= dbConnector.getConn()) {
            String sql ="INSERT INTO EvCo(Username, Password) VALUES(?,?)";
            PreparedStatement pstmt= connection.prepareStatement(sql);
            pstmt.setString(1, c.getUsername());
            pstmt.setString(2, c.getPassword());
            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteCoordinator(int id){
      try(Connection connection=dbConnector.getConn()) {
          String sql="DELETE FROM EvCo WHERE ID=?";
          PreparedStatement pstmt= connection.prepareStatement(sql);
          pstmt.setInt(1, id);
          pstmt.execute();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }

    }

}
