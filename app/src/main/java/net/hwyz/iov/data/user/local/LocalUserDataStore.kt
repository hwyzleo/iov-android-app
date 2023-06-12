package net.hwyz.iov.data.user.local

import androidx.room.rxjava3.EmptyResultSetException
import io.reactivex.rxjava3.core.Single
import net.hwyz.iov.data.user.UserDataStore
import net.hwyz.iov.data.user.local.database.UserDao
import net.hwyz.iov.data.user.model.User

class LocalUserDataStore(private val userDao: UserDao) : UserDataStore {

    override fun getUser(): Single<User> {
        return userDao.getUser().map {
            if(it.username == null) {
                throw EmptyResultSetException("user not present")
            } else {
                User()
            }
        }
    }
}