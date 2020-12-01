package cn.imppp.skin.ui.fragment.main

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.HomeFragmentLayoutBinding
import cn.imppp.skin.ui.activity.FragmentActivity
import cn.imppp.skin.ui.activity.recycleview.RecyclerViewActivity
import cn.imppp.skin.ui.activity.single.SingleRecyclerViewActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.home_fragment_layout.*

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentLayoutBinding>() {
    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.home_fragment_layout
    }

    override fun initView() {
        Log.i("road log", "初始化布局方法  homeFragment")
        btHomeSimpleRecyclerView.setOnClickListener(this)
        btHomeMultiRecyclerView.setOnClickListener(this)
        btHomeMotion.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btHomeSimpleRecyclerView ->
                startActivity(Intent(App.mWindowsContext, SingleRecyclerViewActivity::class.java))
            R.id.btHomeMultiRecyclerView ->
                startActivity(Intent(App.mWindowsContext, RecyclerViewActivity::class.java))
            R.id.btHomeMotion ->
                startActivity(Intent(App.mWindowsContext, FragmentActivity::class.java))
        }
    }

    override fun initData() {
        Log.i("road log", "初始化数据方法    homeFragment")
    }

    override fun observe() {
        Log.i("road log", "观察者方法执行    homeFragment")
    }

    override fun lazyLoadData() {
        Log.i("road log", "懒加载数据内容方法    homeFragment")
    }
}