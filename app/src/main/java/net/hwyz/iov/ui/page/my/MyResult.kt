package net.hwyz.iov.ui.page.my

import net.hwyz.iov.base.MviResult

sealed class MyResult : MviResult {
    object DefaultResult : MyResult()
}