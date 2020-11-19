package cn.imppp.skin.base

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import cn.imppp.skin.repository.LocalRepository
import cn.imppp.skin.theme.Theme

class App :Application() {

    var mCurrentThemes: Int = Theme.LIGHT.mode

    // 创建静态获取方法
    companion object {
        val app: App = App()
        lateinit var mWindowsContext: Context
    }

    override fun onCreate() {
        // 设置默认主题
        mWindowsContext = applicationContext
        super.onCreate()
    }

}