package net.hwyz.iov.utils

object CommonUtil {

    fun genderStr(gender: String): String {
        when(gender) {
            "MALE" -> return "男"
            "FEMALE" -> return "女"
        }
        return "未知"
    }

}