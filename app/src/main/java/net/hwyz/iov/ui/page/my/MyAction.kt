package net.hwyz.iov.ui.page.my

import net.hwyz.iov.base.MviAction

sealed class MyAction : MviAction {
    object CheckLocalUser : MyAction()
}