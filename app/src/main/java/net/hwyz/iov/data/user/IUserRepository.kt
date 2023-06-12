package net.hwyz.iov.data.user

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import net.hwyz.iov.data.user.model.User

/**
 * 用户仓库接口
 */
interface IUserRepository {

    /**
     * 获取本地用户
     */
    fun getLocalUser(): Single<User>

    fun saveUser(user: User): Completable

}