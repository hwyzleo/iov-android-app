package net.hwyz.iov.ui.page.main.profile

import net.hwyz.iov.base.MviAction

sealed class ProfileAction : MviAction {
    object OnStart : ProfileAction()
    object ShowLogoutDialog : ProfileAction()
    object DismissLogoutDialog : ProfileAction()
    object Logout : ProfileAction()
}