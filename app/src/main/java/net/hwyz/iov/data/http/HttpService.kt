package net.hwyz.iov.data.http

import net.hwyz.iov.data.bean.TspResponse
import net.hwyz.iov.data.bean.UserInfo
import retrofit2.http.*

/**
 * Http服务接口
 *
 * @author hwyz_leo
 */
interface HttpService {

    companion object {
        const val url = "http://10.0.68.191:8081"
    }

    // 发送登录验证码
    @FormUrlEncoded
    @POST("/login/sendVerifyCode")
    suspend fun sendLoginVerifyCode(@Field("mobile") mobile: String): TspResponse<Void>

    // 验证码登录
    @FormUrlEncoded
    @POST("/login/verifyCodeLogin")
    suspend fun verifyCodeLogin(
        @Field("mobile") mobile: String,
        @Field("verifyCode") verifyCode: String
    ): TspResponse<UserInfo>

}
