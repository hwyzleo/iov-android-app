package net.hwyz.iov.utils

import net.hwyz.iov.data.bean.LoginResponse
import net.hwyz.iov.data.store.DataStoreUtils

/**
 * APP用户工具类
 *
 * @author hwyz_leo
 */
object AppUserUtil {
    private const val LOGGED_FLAG = "logged_flag"
    private const val NICKNAME = "nickname"
    private const val TOKEN = "token"
    var isLogged: Boolean
        get() = DataStoreUtils.readBooleanData(LOGGED_FLAG, false)
        set(value) = DataStoreUtils.saveSyncBooleanData(LOGGED_FLAG, value = value)

    var nickname: String
        get() = DataStoreUtils.readStringData(NICKNAME, "")
        set(value) = DataStoreUtils.saveSyncStringData(NICKNAME, value = value)

    var token: String
        get() = DataStoreUtils.readStringData(TOKEN, "")
        set(value) = DataStoreUtils.saveSyncStringData(TOKEN, value = value)

    fun changeNickname(nickname: String) {
        if (isLogged) {
            this.nickname = nickname
        }
    }

    fun onLogin(userInfo: LoginResponse) {
        isLogged = true
        this.nickname = userInfo.nickname
        this.token = userInfo.token
    }

    fun onLogOut() {
        isLogged = false
        this.nickname = ""
        this.token = ""
    }
}