package net.hwyz.iov.ui.page.my

import net.hwyz.iov.base.MviState
import net.hwyz.iov.data.bean.LoginResponse

data class MyState(
    var isLogged: Boolean = false,
    var nickname: String = "",
    val showLogout: Boolean = false
) : MviState