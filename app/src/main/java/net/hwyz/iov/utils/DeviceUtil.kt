package net.hwyz.iov.utils

import net.hwyz.iov.data.store.DataStoreUtils
import java.util.UUID

object DeviceUtil {

    fun getDeviceId(): String {
        var deviceId = DataStoreUtils.readStringData("deviceId")
        if (deviceId.isEmpty()) {
            deviceId = UUID.randomUUID().toString()
            DataStoreUtils.putSyncData("deviceId", deviceId)
        }
        return deviceId
    }
}