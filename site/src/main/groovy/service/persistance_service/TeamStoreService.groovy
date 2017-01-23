package service.persistance_service

import ratpack.exec.Operation
import ratpack.exec.Promise

interface TeamStoreService<T> {

    Operation save(T object)

    Operation delete(String id)

    Promise<List> fetchAll()

    Promise<T> fetchById(String id)


}
