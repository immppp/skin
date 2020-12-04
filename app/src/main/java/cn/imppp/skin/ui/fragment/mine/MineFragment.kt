package cn.imppp.skin.ui.fragment.mine

import android.util.Log
import android.view.View
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.MineFragmentLayoutBinding
import cn.imppp.skin.utils.MmkvUtils
import kotlinx.android.synthetic.main.mine_fragment_layout.*

class MineFragment : BaseFragment<MineViewModel, MineFragmentLayoutBinding>() {
    override fun viewModelClass(): Class<MineViewModel> {
        return MineViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.mine_fragment_layout
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
        }
    }

    override fun initView() {
    }

    override fun initData() {
        tvMineUser.text = MmkvUtils.decodeString(Constant.spUserName)
    }

    override fun observe() {
        Log.i("road log", "观察者方法执行    mineFragment")
    }

    override fun lazyLoadData() {
        Log.i("road log", "懒加载数据内容方法    mineFragment")
    }
}