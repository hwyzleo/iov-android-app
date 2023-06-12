package net.hwyz.iov.data.user.remote

import io.reactivex.rxjava3.core.Single
import net.hwyz.iov.data.user.UserDataStore
import net.hwyz.iov.data.user.model.User

class RemoteUserDataStore() : UserDataStore {

    override fun getUser(): Single<User> {
        return Single.error(UnsupportedOperationException("User not supported in remote"))
    }
}