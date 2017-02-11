package service.persistance_service

import models.Player
import ratpack.exec.Operation
import ratpack.exec.Promise

interface TeamStoreService<T> {

    Operation save(T object)

    Operation delete(String id)

    Promise<List> fetchAll()

    Promise<List<Player>> fetchPlayers()

    Promise<T> fetchById(String id)


}
