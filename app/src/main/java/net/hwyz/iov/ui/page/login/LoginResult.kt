package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviResult

sealed class LoginResult : MviResult {
    object InitResult : LoginResult()
    data class UpdateMobileResult(val mobile: String) : LoginResult()
    object ClearMobileResult : LoginResult()
    object SendVerifyCodeResult : LoginResult() {
        object Success : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
        object InProgress : LoginResult()
    }

    data class UpdateVerifyCodeResult(val verifyCode: String) : LoginResult()
    object ClearVerifyCodeResult : LoginResult()
    object VerifyCodeLoginResult : LoginResult() {
        object Success : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
        object InProgress : LoginResult()
    }
}