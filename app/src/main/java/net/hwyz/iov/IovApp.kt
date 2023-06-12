package net.hwyz.iov

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import net.hwyz.iov.data.store.DataStoreUtils

@HiltAndroidApp
class IovApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
        DataStoreUtils.init(this)
    }
}