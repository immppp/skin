package cn.imppp.skin.ui.activity.web

import cn.imppp.skin.R
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.ActivityWebViewLayoutBinding
import kotlinx.android.synthetic.main.activity_web_view_layout.*

class WebViewActivity : BaseActivity<WebViewViewModel, ActivityWebViewLayoutBinding>() {
    override fun viewModelClass(): Class<WebViewViewModel> {
        return WebViewViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_web_view_layout
    }

    override fun initData() {
        mViewModel.webUrl.value = intent.getStringExtra(Constant.url)
        webView.loadUrl(mViewModel.webUrl.value)
    }
}