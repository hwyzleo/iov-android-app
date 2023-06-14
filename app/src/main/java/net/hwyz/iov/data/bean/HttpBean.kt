package net.hwyz.iov.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
    var token: String,
    var username: String,
) : Parcelable
