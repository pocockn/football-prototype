package service.persistance_service

import models.Player
import ratpack.exec.Operation
import ratpack.exec.Promise

interface PlayerStoreService<T> {

    Operation save(T object)

    Operation delete(String id)

    Promise<List<Player>> fetchAll()

    Promise<Player> fetchById(String id)

    Promise<List<Player>> fetchByIds(List<Player> ids)

}
