package cn.imppp.skin.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import cn.imppp.skin.repository.LocalRepository
import cn.imppp.skin.theme.Theme
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*

open class BaseActivity(var layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (delegate.localNightMode != Theme.LIGHT.mode || delegate.localNightMode != Theme.DARK.mode) {
            // 表示未设置主题
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
            delegate.localNightMode = App.app.mCurrentThemes
        }
        if (LocalRepository.getInstance().getCurrentTheme() != App.app.mCurrentThemes) {
            delegate.localNightMode = App.app.mCurrentThemes
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
        }
        super.onCreate(savedInstanceState)
        if (App.app.mCurrentThemes == Theme.LIGHT.mode) {
            Log.i("BaseActivity", "aa change black")
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
        ivBack.onClick {
            Toast.makeText(this, "返回被点击", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}