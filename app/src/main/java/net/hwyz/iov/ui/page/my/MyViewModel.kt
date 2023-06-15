package net.hwyz.iov.ui.page.my

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.hwyz.iov.base.MviActionProcessor
import net.hwyz.iov.base.presentation.BaseViewModel
import net.hwyz.iov.data.bean.UserInfo
import net.hwyz.iov.ui.page.login.LoginAction
import net.hwyz.iov.ui.page.login.LoginIntent
import net.hwyz.iov.ui.page.login.LoginResult
import net.hwyz.iov.ui.page.login.LoginState
import net.hwyz.iov.utils.AppUserUtil
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    override val actionProcessor: MyProcessor
) : BaseViewModel<MyIntent, LoginState, MyAction, MyResult>() {
    var viewStates by mutableStateOf(MyState(userInfo = AppUserUtil.userInfo))
        private set

    fun dispatch(intent: MyIntent) {
        var action = actionFromIntent(intent)
        viewModelScope.launch {
            flow {
                var result = actionProcessor.executeAction(action)
                emit(reducer(result))
            }.collect()
        }
    }

    override fun actionFromIntent(intent: MyIntent): MyAction {
        return when (intent) {
            is MyIntent.InitIntent -> TODO()
            is MyIntent.LoginIntent -> MyAction.LoginAction
        }
    }

    private fun onStart() {
        viewStates =
            viewStates.copy(isLogged = AppUserUtil.isLogged, userInfo = AppUserUtil.userInfo)
    }

    private fun logout() {
        AppUserUtil.onLogOut()
        viewStates = viewStates.copy(isLogged = false, showLogout = false, userInfo = null)
    }

    private fun dismissLogout() {
        viewStates = viewStates.copy(showLogout = false)
    }

    private fun showLogout() {
        viewStates = viewStates.copy(showLogout = true)
    }

    override suspend fun reducer(result: MyResult) {
        TODO("Not yet implemented")
    }


}