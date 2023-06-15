package net.hwyz.iov.ui.page.my

import net.hwyz.iov.data.bean.UserInfo

data class MyState(
    var isLogged: Boolean = false,
    var userInfo: UserInfo?,
    val showLogout: Boolean = false
)