package cn.imppp.skin.ui.activity

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cn.imppp.skin.PersonProtos
import cn.imppp.skin.R
import cn.imppp.skin.adapter.main.MainPagerAdapter
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityMainBinding
import cn.imppp.skin.state.MainViewModel
import cn.imppp.skin.ui.activity.single.SingleRecyclerViewActivity
import cn.imppp.skin.ui.fragment.main.HomeFragment
import cn.imppp.skin.ui.fragment.mine.MineFragment
import cn.imppp.skin.utils.MmkvUtils
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        QMUIStatusBarHelper.translucent(this, Color.TRANSPARENT)

        btNextPage.setOnClickListener(this)
        btLight.setOnClickListener(this)
        btDark.setOnClickListener(this)
        btSingleRecyclerView.setOnClickListener(this)

        mViewModel.haveTitleLayout.value = false

        val mFragments = mutableListOf<Fragment>()
        mFragments.add(HomeFragment())
        mFragments.add(MineFragment())

        val mainAdapter = MainPagerAdapter(this, mFragments)
        viewPager.adapter = mainAdapter
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
            R.id.btSingleRecyclerView -> {
                startActivity(Intent(App.mWindowsContext, SingleRecyclerViewActivity::class.java))
            }
        }
    }

}
