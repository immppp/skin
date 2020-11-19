package cn.imppp.skin.ui.activity

import android.os.Bundle
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.repository.LocalRepository
import cn.imppp.skin.theme.Theme
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_layout.*

class FragmentActivity : BaseActivity(R.layout.activity_fragment) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text = "FragmentActivity"
        btDark.onClick {
            LocalRepository.getInstance().saveCurrentTheme(Theme.DARK.mode)
            recreate()
        }
        btLight.onClick {
            LocalRepository.getInstance().saveCurrentTheme(Theme.LIGHT.mode)
            recreate()
        }
    }

}