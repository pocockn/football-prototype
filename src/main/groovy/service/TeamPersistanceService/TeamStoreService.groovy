package service.TeamPersistanceService

import models.Team
import ratpack.exec.Operation
import ratpack.exec.Promise

interface TeamStoreService {

    Operation save(Team team)

    Operation delete(String id)

    Promise<List<Team>> fetchAll()

    Promise<Team> fetchById(String id)


}
