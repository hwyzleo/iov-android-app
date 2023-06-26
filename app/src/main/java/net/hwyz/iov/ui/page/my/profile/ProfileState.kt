package net.hwyz.iov.ui.page.my.profile

import net.hwyz.iov.base.MviState

data class ProfileState(
    var result: ProfileResult = ProfileResult.DisplayProfile,
    var avatar: String? = null,
    var nickname: String = "",
    var gender: String = "UNKNOWN"
) : MviState