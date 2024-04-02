package BLL;

import BE.Coordinator;
import DAL.CoordinatorDAO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CoordinatorLogic {
    CoordinatorDAO coordinatorDAO= new CoordinatorDAO();

    public void createCoordinator(Coordinator coordinator){
        coordinatorDAO.createCoordinator(coordinator);
    }

    public Coordinator getCoordinator(){
        return coordinatorDAO.getCoordinator();
    }

    public String getCoordinatorbyId(int coorId){
        return coordinatorDAO.getCoordinatorbyId(coorId);
    }

    public ArrayList<Coordinator> getCoordinatorAll(){
        System.out.println(coordinatorDAO.getCoordinatorAll());
        return coordinatorDAO.getCoordinatorAll();
    }

    public void deleteCordinator(int id){
        coordinatorDAO.deleteCoordinator(id);
    }
}
