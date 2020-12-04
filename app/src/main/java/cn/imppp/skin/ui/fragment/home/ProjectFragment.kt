package cn.imppp.skin.ui.fragment.home

import android.view.View
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentProjectLayoutBinding
import cn.imppp.skin.ui.fragment.main.HomeViewModel

class ProjectFragment : BaseFragment<HomeViewModel, FragmentProjectLayoutBinding>() {
    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onClick(p0: View?) {
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_project_layout
    }

}