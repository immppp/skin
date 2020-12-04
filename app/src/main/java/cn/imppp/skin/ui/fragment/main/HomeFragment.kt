package cn.imppp.skin.ui.fragment.main

import android.view.View
import androidx.fragment.app.Fragment
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import cn.imppp.skin.R
import cn.imppp.skin.adapter.home.HomePagerAdapter
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.HomeFragmentLayoutBinding
import cn.imppp.skin.ui.fragment.home.MainFragment
import cn.imppp.skin.ui.fragment.home.ProjectFragment
import cn.imppp.skin.ui.fragment.home.SquareFragment
import cn.imppp.skin.ui.fragment.home.SystemFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.home_fragment_layout.*

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentLayoutBinding>(), OnViewClickListener {

    private val titles =
        arrayOf("热门", "广场", "体系", "项目")

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.home_fragment_layout
    }

    override fun initView() {
        // 加载viewPager
        val mFragments = mutableListOf<Fragment>()
        mFragments.add(MainFragment())
        mFragments.add(SquareFragment())
        mFragments.add(SystemFragment())
        mFragments.add(ProjectFragment())
        val homePagerAdapter = HomePagerAdapter(this.requireActivity(), mFragments)
        vpHomeFragment.adapter = homePagerAdapter
        val tabLayoutMediator = TabLayoutMediator(tlHome, vpHomeFragment, strategy())
        tabLayoutMediator.attach()
    }

    override fun onClick(p0: View?) {
    }

    override fun initData() {
    }

    override fun observe() {
    }

    override fun lazyLoadData() {
    }

    override fun onRecycleViewClick(view: View) {
    }

    inner class strategy : TabLayoutMediator.TabConfigurationStrategy {
        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
            tab.text = titles[position]
        }

    }
}