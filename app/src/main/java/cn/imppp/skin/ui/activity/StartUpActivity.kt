package cn.imppp.skin.ui.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.ui.activity.demo.CardViewActivity
import cn.imppp.skin.ui.activity.login.LoginActivity
import cn.imppp.skin.utils.MmkvUtils
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_start_up.*


class StartUpActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QMUIStatusBarHelper.translucent(this, Color.TRANSPARENT)
        QMUIDisplayHelper.setFullScreen(this)
        setContentView(R.layout.activity_start_up)

        alvStartUp.addGradientAnimListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                if (!TextUtils.isEmpty(MmkvUtils.decodeString(Constant.spCookie))) {
                    startActivity(Intent(App.mWindowsContext, LoginActivity::class.java))
                } else {
                    startActivity(Intent(App.mWindowsContext, LoginActivity::class.java))
                }
                finish()
            }
        })
        alvStartUp.startAnimation()
    }

}