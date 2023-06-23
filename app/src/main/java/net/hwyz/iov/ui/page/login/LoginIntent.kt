package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviIntent

sealed class LoginIntent : MviIntent {
    object OnLaunched : LoginIntent()
    data class SendVerifyCode(val countryRegionCode: String, val mobile: String) : LoginIntent()
    data class VerifyCodeLogin(val verifyCode: String) : LoginIntent()
}