package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviAction

sealed class LoginAction : MviAction {
    object DisplayStep1: LoginAction()
    object DisplayLoading : LoginAction()
    data class SendVerifyCode(
        val countryRegionCode: String,
        val mobile: String
    ) : LoginAction()
    data class VerifyCodeLogin(
        val countryRegionCode: String,
        val mobile: String,
        val verifyCode: String
    ) : LoginAction()
}