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
        const val url = "http://192.168.2.223:8081"
    }

    // 发送登录验证码
    @FormUrlEncoded
    @POST("/mp/login/sendVerifyCode")
    suspend fun sendLoginVerifyCode(
        @Field("countryRegionCode") countryRegionCode: String,
        @Field("mobile") mobile: String
    ): TspResponse<Void>

    // 验证码登录
    @FormUrlEncoded
    @POST("/mp/login/verifyCodeLogin")
    suspend fun verifyCodeLogin(
        @Field("countryRegionCode") countryRegionCode: String,
        @Field("mobile") mobile: String,
        @Field("verifyCode") verifyCode: String
    ): TspResponse<UserInfo>

}
