package cn.imppp.skin.ui.fragment.home

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.MultiTypeAdapter
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.skin.R
import cn.imppp.skin.adapter.mainArticle.MainProjectBinder
import cn.imppp.skin.adapter.mainArticle.MainSquareBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentProjectLayoutBinding
import cn.imppp.skin.ui.fragment.main.HomeViewModel
import kotlinx.android.synthetic.main.fragment_project_layout.*
import kotlinx.android.synthetic.main.fragment_systems_layout.*

class ProjectFragment : BaseFragment<HomeViewModel, FragmentProjectLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onClick(p0: View?) {
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_project_layout
    }

    override fun initView() {
        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvProject.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvProject, GridLayoutManager(App.mWindowsContext, 2))
    }

    override fun observe() {
        mViewModel.projectList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    add(MainProjectBinder(it[i]).apply {
                        setOnClickListener(this@ProjectFragment::onRecycleViewClick)
                    })
                }
            }
            )
        })
    }

    override fun initData() {
        mViewModel.loadProjectTypeList()
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
    }
}