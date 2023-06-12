package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviAction

sealed class LoginAction : MviAction {
    object InitAction : LoginAction()
    data class UpdateMobileAction(val mobile: String) : LoginAction()
    object ClearMobileAction : LoginAction()
    data class SendVerifyCodeAction(val mobile: String) : LoginAction()
    data class UpdateVerifyCodeAction(val verifyCode: String) : LoginAction()
    object ClearVerifyCodeAction : LoginAction()
    data class VerifyCodeLoginAction(val mobile: String, val verifyCode: String) : LoginAction()
}