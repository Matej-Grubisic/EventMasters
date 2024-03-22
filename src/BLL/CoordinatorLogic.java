package BLL;

import BE.Coordinator;
import DAL.CoordinatorDAO;

public class CoordinatorLogic {
    CoordinatorDAO coordinatorDAO= new CoordinatorDAO();

    public void createCoordinator(Coordinator coordinator){
        coordinatorDAO.createCoordinator(coordinator);
    }
}
