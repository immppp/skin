package cn.imppp.skin.ui.fragment.mine

import android.util.Log
import android.view.View
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.MineFragmentLayoutBinding

class MineFragment : BaseFragment<MineViewModel, MineFragmentLayoutBinding>() {
    override fun viewModelClass(): Class<MineViewModel> {
        return MineViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.mine_fragment_layout
    }

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        Log.i("road log", "初始化布局方法  mineFragment")
    }

    override fun initData() {
        Log.i("road log", "初始化数据方法    mineFragment")
    }

    override fun observe() {
        Log.i("road log", "观察者方法执行    mineFragment")
    }

    override fun lazyLoadData() {
        Log.i("road log", "懒加载数据内容方法    mineFragment")
    }
}