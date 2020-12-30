package cn.imppp.skin.ui.activity.web

import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel

class WebViewViewModel : BaseViewModel() {
    val webUrl = MutableLiveData<String>("https://www.baidu.com/")
}