package net.hwyz.iov.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

/**
 * TSP平台通用响应实体
 */
data class TspResponse<T>(
    var code: Int,
    var message: String,
    var ts: Long,
    var data: T?
)

/**
 * 用户信息
 */
@Parcelize
data class UserInfo(
    var mobile: String,
    var nickname: String,
    var token: String,
    var tokenExpires: Long,
    var refreshToken: String,
    var refreshTokenExpires: Long
) : Parcelable
