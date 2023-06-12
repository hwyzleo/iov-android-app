package net.hwyz.iov.data.user

import io.reactivex.rxjava3.core.Single
import net.hwyz.iov.data.user.model.User

interface UserDataStore {

    fun getUser(): Single<User>

}