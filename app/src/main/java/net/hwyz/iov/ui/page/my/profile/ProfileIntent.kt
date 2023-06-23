package net.hwyz.iov.ui.page.my.profile

import net.hwyz.iov.base.MviIntent

sealed class ProfileIntent : MviIntent {
    object OnLaunched : ProfileIntent()
    object OnTapBackProfile : ProfileIntent()
    object OnTapNickname : ProfileIntent()
    data class OnTapNicknameSaveButton(val nickname: String) : ProfileIntent()
    object OnTapGender: ProfileIntent()
    data class OnTapGenderSaveButton(val gender: String) : ProfileIntent()
}