package service.user_service

import ratpack.exec.Operation
import ratpack.exec.Promise

interface UserStorageService<T> {

    Promise<List<?>> fetchAll()

    Promise<?> fetch(String id)

    Operation delete(String id)

    Promise<?> saveUser(T t)
}
