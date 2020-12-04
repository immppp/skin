package cn.imppp.skin.ui.fragment.home

import android.view.View
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentSystemsLayoutBinding
import cn.imppp.skin.ui.fragment.main.HomeViewModel

/**
 * 体系界面
 */

class SystemFragment : BaseFragment<HomeViewModel, FragmentSystemsLayoutBinding>() {
    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_systems_layout
    }

    override fun onClick(p0: View?) {
    }
}