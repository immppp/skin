package cn.imppp.skin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.repository.LocalRepository
import cn.imppp.skin.state.ThemeViewModel
import cn.imppp.skin.theme.Theme
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {
    private lateinit var mThemeViewModel: ThemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //配置 primaryColor 是否全屏，在配置布局之前
//        with(mThemeViewModel) {
//            primaryColor.value?.let { setTheme(it) }
//            edgeToEdgeEnabled.value?.let { applyEdgeToEdgePreference(it) }
//        }
        super.onCreate(savedInstanceState)
        mThemeViewModel = ViewModelProvider(this).get(ThemeViewModel::class.java)
        btDark.onClick {
            LocalRepository.getInstance().saveCurrentTheme(Theme.DARK.mode)
            if (App.app.mCurrentThemes != LocalRepository.getInstance().getCurrentTheme()) {
                changeMode()
            }
        }
        btLight.onClick {
            LocalRepository.getInstance().saveCurrentTheme(Theme.LIGHT.mode)
            if (App.app.mCurrentThemes != LocalRepository.getInstance().getCurrentTheme()) {
                changeMode()
            }
        }

        btNextPage.onClick { startActivity(Intent(this, FragmentActivity::class.java)) }
    }

}
