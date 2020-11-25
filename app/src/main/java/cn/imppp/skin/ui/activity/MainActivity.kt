package cn.imppp.skin.ui.activity

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityMainBinding
import cn.imppp.skin.state.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        btNextPage.setOnClickListener(this)
        btLight.setOnClickListener(this)
        btDark.setOnClickListener(this)
        mViewModel.backBottom.value = false
    }

    override fun loadData() {
        mViewModel.login()
    }

    override fun observer() {
        mViewModel.userLiveData.observe(this, Observer {
            btDark.text = it.username
            btLight.text = it.parentid
        })
    }

    override fun onClick(p0: View?) {
        super.onClick(p0)
        when (p0?.id) {
            R.id.btNextPage -> {
                startActivity(Intent(App.mWindowsContext, FragmentActivity::class.java))
            }
            R.id.btLight -> {
                mViewModel.login()
            }
        }
    }

}
