package cn.imppp.skin.base

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import cn.imppp.netmodlelibrary.utils.Utils
import cn.imppp.skin.theme.Theme
import cn.imppp.skin.utils.MmkvUtils
import com.tencent.mmkv.MMKV

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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Utils.init(mWindowsContext)
        // 初始化mmkv存储
        MMKV.initialize(this)
        MmkvUtils.instance
        // 初始化数据库
        AppDatabase.getInstance(mWindowsContext)
    }

}