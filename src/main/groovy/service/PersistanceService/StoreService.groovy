package service.PersistanceService

import ratpack.exec.Operation
import ratpack.exec.Promise

interface StoreService<T> {

    Operation save(T object)

    Operation delete(String id)

    Promise<List<T>> fetchAll()

    Promise<T> fetchById(String id)


}
