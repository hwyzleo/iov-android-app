package net.hwyz.iov.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Response<T>(
    var data: T?,
    var code: Int,
    var message: String
)

@Parcelize
data class UserInfo(
    var token: String,
    var username: String,
) : Parcelable
