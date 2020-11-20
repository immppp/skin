package cn.imppp.skin.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.imppp.skin.repository.LocalRepository
import cn.imppp.skin.theme.Theme
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.title_layout.*


open class BaseActivity(layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
//        Log.i("BaseActivity", "初始化状态  ${delegate.localNightMode}")
        Log.i("BaseActivity", "${this.localClassName} onCreate  ${delegate.localNightMode}")
        if (delegate.localNightMode != LocalRepository.getInstance().getCurrentTheme()) {
            delegate.localNightMode = LocalRepository.getInstance().getCurrentTheme()
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
        }
        super.onCreate(savedInstanceState)
        ivBack.onClick {
            Toast.makeText(this, "返回被点击", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onStart() {
        Log.i("BaseActivity", "${this.localClassName} onStart  ${delegate.localNightMode}")
        super.onStart()
    }

    override fun onRestart() {
        if (delegate.localNightMode != LocalRepository.getInstance().getCurrentTheme()) {
            delegate.localNightMode = LocalRepository.getInstance().getCurrentTheme()
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
        }
        Log.i("BaseActivity", "${this.localClassName} onRestart  ${delegate.localNightMode}")
        super.onRestart()
    }

    override fun onResume() {
        if (App.app.mCurrentThemes != Theme.DARK.mode) {
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        }
        Log.i("BaseActivity", "${this.localClassName} onResume  ${delegate.localNightMode}")
        super.onResume()
    }

    override fun onPause() {
        Log.i("BaseActivity", "${this.localClassName} onPause  ${delegate.localNightMode}")
        super.onPause()
    }

    override fun onStop() {
        Log.i("BaseActivity", "${this.localClassName} onStop  ${delegate.localNightMode}")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i("BaseActivity", "${this.localClassName} onDestroy  ${delegate.localNightMode}")
        super.onDestroy()
    }

    fun changeMode() {
        if (LocalRepository.getInstance().getCurrentTheme() != App.app.mCurrentThemes) {
            Log.i("BaseActivity", "变更状态")
            delegate.localNightMode = LocalRepository.getInstance().getCurrentTheme()
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
        }
    }

}