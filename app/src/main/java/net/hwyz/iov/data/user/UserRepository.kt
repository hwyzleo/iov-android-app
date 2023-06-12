package net.hwyz.iov.data.user

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import net.hwyz.iov.data.user.model.User

class UserRepository(
    private val localDataStore: UserDataStore,
    private val remoteDataStore: UserDataStore
) : IUserRepository {

    override fun getLocalUser(): Single<User> {
        return localDataStore.getUser()
    }

    override fun saveUser(user: User): Completable {
        TODO("Not yet implemented")
    }

}