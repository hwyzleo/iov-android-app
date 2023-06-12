package net.hwyz.iov.data.user.local.database

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Query("select * from user limit 1")
    fun getUser(): Single<UserEntity>

}