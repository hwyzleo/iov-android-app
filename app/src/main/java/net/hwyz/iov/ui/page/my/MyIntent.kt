package net.hwyz.iov.ui.page.my

import net.hwyz.iov.base.MviIntent

sealed class MyIntent : MviIntent {
    object OnLaunched : MyIntent()
}