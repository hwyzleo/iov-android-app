package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviState

data class LoginState(
    val mobile: String = "",
    val verifyCode: String = "",
    val isAgree: Boolean = false,
    val isSendVerifyCode: Boolean = false,
    val isLogged: Boolean = false
) : MviState {
    companion object {
        fun initialState(): LoginState = LoginState()
    }
}