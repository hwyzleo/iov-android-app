package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.MviIntent

sealed class LoginIntent : MviIntent {
    object InitIntent : LoginIntent()
    data class SelectCountryRegionIntent(val countryRegionCode: String) : LoginIntent()
    data class UpdateMobileIntent(val mobile: String) : LoginIntent()
    object ClearMobileIntent : LoginIntent()
    data class UpdateAgreeIntent(val isAgree: Boolean) : LoginIntent()
    data class SendVerifyCodeIntent(val countryRegionCode: String) : LoginIntent()
    data class UpdateVerifyCodeIntent(val verifyCode: String) : LoginIntent()
    object ClearVerifyCodeIntent : LoginIntent()
    object VerifyCodeLoginIntent : LoginIntent()
}