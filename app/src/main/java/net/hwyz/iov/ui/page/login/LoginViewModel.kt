package net.hwyz.iov.ui.page.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.hwyz.iov.base.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    override val actionProcessor: LoginProcessor
) : BaseViewModel<LoginIntent, LoginState, LoginAction, LoginResult>() {
    var viewStates by mutableStateOf(LoginState())
    private val _viewEvents = Channel<LoginViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    fun dispatch(intent: LoginIntent) {
        var action = actionFromIntent(intent)
        viewModelScope.launch {
            flow {
                var result = actionProcessor.executeAction(action)
                emit(reducer(result))
            }.collect()
        }
    }

    override fun actionFromIntent(intent: LoginIntent): LoginAction {
        return when (intent) {
            is LoginIntent.InitIntent -> TODO()
            is LoginIntent.UpdateMobileIntent -> LoginAction.UpdateMobileAction(intent.mobile)
            is LoginIntent.ClearMobileIntent -> LoginAction.ClearMobileAction
            is LoginIntent.UpdateAgreeIntent -> TODO()
            is LoginIntent.SendVerifyCodeIntent -> LoginAction.SendVerifyCodeAction(viewStates.mobile)
            is LoginIntent.UpdateVerifyCodeIntent -> LoginAction.UpdateVerifyCodeAction(intent.verifyCode)
            is LoginIntent.ClearVerifyCodeIntent -> LoginAction.ClearVerifyCodeAction
            is LoginIntent.VerifyCodeLoginIntent -> LoginAction.VerifyCodeLoginAction(
                viewStates.mobile,
                viewStates.verifyCode
            )
        }
    }

    override suspend fun reducer(result: LoginResult) {
        when (result) {
            is LoginResult.InitResult -> TODO()
            is LoginResult.UpdateMobileResult -> updateMobile(result.mobile)
            is LoginResult.ClearMobileResult -> clearMobile()
            is LoginResult.SendVerifyCodeResult.Success -> sendVerifyCodeSuccess()
            is LoginResult.SendVerifyCodeResult.Failure -> sendVerifyCodeFailure(
                result.error.message ?: ""
            )

            is LoginResult.UpdateVerifyCodeResult -> updateVerifyCode(result.verifyCode)
            is LoginResult.ClearVerifyCodeResult -> clearVerifyCode()
            is LoginResult.VerifyCodeLoginResult.Success -> verifyCodeLoginSuccess()
            is LoginResult.VerifyCodeLoginResult.Failure -> verifyCodeLoginFailure(
                result.error.message ?: ""
            )
        }
    }

    private fun updateMobile(mobile: String) {
        viewStates = viewStates.copy(mobile = mobile)
    }

    private fun clearMobile() {
        viewStates = viewStates.copy(mobile = "")
    }

    private fun updateAgree(isAgree: Boolean) {
        viewStates = viewStates.copy(isAgree = isAgree)
    }

    private fun sendVerifyCodeSuccess() {
        viewStates = viewStates.copy(isSendVerifyCode = true)
    }

    private suspend fun sendVerifyCodeFailure(error: String) {
        _viewEvents.send(LoginViewEvent.ErrorMessage(error))
    }

    private fun updateVerifyCode(verifyCode: String) {
        viewStates = viewStates.copy(verifyCode = verifyCode)
    }

    private fun clearVerifyCode() {
        viewStates = viewStates.copy(verifyCode = "")
    }

    private suspend fun verifyCodeLoginSuccess() {
        viewStates = viewStates.copy(isLogged = true)
        _viewEvents.send(LoginViewEvent.PopBack)
    }

    private suspend fun verifyCodeLoginFailure(error: String) {
        _viewEvents.send(LoginViewEvent.ErrorMessage(error))
    }

}

/**
 * 一次性事件
 */
sealed class LoginViewEvent {
    object PopBack : LoginViewEvent()
    data class ErrorMessage(val message: String) : LoginViewEvent()
}