package DAL;

import BE.Coordinator;

public interface ICoordinatorDAO {

    Coordinator getCoordinator();
    void createCoordinator(Coordinator c);
}
