package `in`.spiegel.datastorage

import android.app.Application
import android.content.res.Resources
import android.os.Build
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Vijay on 12/6/2021.
 */
@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, getSystemLanguageCode())

    }
    fun getSystemLanguageCode(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources.getSystem().configuration.locales[0].language

        } else {
            Resources.getSystem().configuration.locale.language

        }
    }
}