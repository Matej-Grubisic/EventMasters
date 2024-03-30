package DAL;

import BE.Coordinator;

import java.util.ArrayList;

public interface ICoordinatorDAO {

    Coordinator getCoordinator();

    ArrayList<Coordinator> getCoordinatorAll();

    void createCoordinator(Coordinator c);

    void deleteCoordinator(int id);
}
