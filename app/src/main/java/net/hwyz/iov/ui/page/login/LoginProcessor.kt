package net.hwyz.iov.ui.page.login

import net.hwyz.iov.base.presentation.MviActionProcessor
import net.hwyz.iov.data.http.HttpService
import net.hwyz.iov.utils.AppUserUtil
import javax.inject.Inject

open class LoginProcessor @Inject constructor(
    private var service: HttpService,
) :
    MviActionProcessor<LoginAction, LoginResult> {

    override suspend fun executeAction(action: LoginAction): LoginResult {
        return when (action) {
            is LoginAction.InitAction -> TODO()
            is LoginAction.UpdateMobileAction -> LoginResult.UpdateMobileResult(action.mobile)
            is LoginAction.ClearMobileAction -> LoginResult.ClearMobileResult
            is LoginAction.SendVerifyCodeAction -> sendVerifyCode(action.mobile)
            is LoginAction.UpdateVerifyCodeAction -> LoginResult.UpdateVerifyCodeResult(action.verifyCode)
            is LoginAction.ClearVerifyCodeAction -> LoginResult.ClearVerifyCodeResult
            is LoginAction.VerifyCodeLoginAction -> verifyCodeLogin(
                action.mobile,
                action.verifyCode
            )
        }
    }

    private suspend fun sendVerifyCode(mobile: String): LoginResult {
        return try {
            val response = service.sendLoginVerifyCode(mobile.trim())
            if (response.code == 0) {
                LoginResult.SendVerifyCodeResult.Success
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            LoginResult.SendVerifyCodeResult.Failure(e)
        }
    }

    private suspend fun verifyCodeLogin(
        mobile: String,
        verifyCode: String
    ): LoginResult {
        return try {
            val response = service.verifyCodeLogin(mobile.trim(), verifyCode.trim())
            if (response.code == 0) {
                AppUserUtil.onLogin(response.data!!)
                LoginResult.VerifyCodeLoginResult.Success
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            LoginResult.VerifyCodeLoginResult.Failure(e)
        }
    }
}