package cn.imppp.skin.ui.activity

import cn.imppp.skin.R
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.state.MainViewModel

class FragmentActivity : BaseActivity<MainViewModel>() {

    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_fragment
    }

    override fun loadData() {
        mViewModel.login()
    }

}