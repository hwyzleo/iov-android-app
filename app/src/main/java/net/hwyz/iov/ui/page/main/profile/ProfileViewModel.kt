package net.hwyz.iov.ui.page.main.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.data.bean.UserInfo
import net.hwyz.iov.utils.AppUserUtil
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    var viewStates by mutableStateOf(ProfileViewState())
        private set

    fun dispatch(action: ProfileAction) {
        when (action) {
            is ProfileAction.OnStart -> onStart()
            is ProfileAction.ShowLogoutDialog -> showLogout()
            is ProfileAction.DismissLogoutDialog -> dismissLogout()
            is ProfileAction.Logout -> logout()
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


}

data class ProfileViewState(
    val isLogged: Boolean = AppUserUtil.isLogged,
    val userInfo: UserInfo? = AppUserUtil.userInfo,
    val showLogout: Boolean = false
)