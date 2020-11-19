package cn.imppp.skin.state

import android.content.res.Resources
import androidx.annotation.StyleRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.imppp.skin.theme.Theme

class ThemeViewModel  : ViewModel() {
    val primaryColor = MutableLiveData<@StyleRes Int>()
    val currentTheme = MutableLiveData<Theme>(
        Theme.SYSTEM)
    val edgeToEdgeEnabled = MutableLiveData<Boolean>(false)

    fun setCurrentTheme(theme: Theme) {
        //防抖
        if (theme == currentTheme.value) return
        currentTheme.value = theme
    }
}