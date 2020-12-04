package cn.imppp.skin.ui.fragment.home

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.MultiTypeAdapter
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.skin.R
import cn.imppp.skin.adapter.mainArticle.MainSystemBinder
import cn.imppp.skin.adapter.mainArticle.MainSystemContainerBinder
import cn.imppp.skin.adapter.mainArticle.MainSystemItemBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentSystemsLayoutBinding
import cn.imppp.skin.ui.fragment.main.HomeViewModel
import kotlinx.android.synthetic.main.fragment_systems_layout.*
import kotlinx.android.synthetic.main.square_fragment_layout.*

/**
 * 体系界面
 */

class SystemFragment : BaseFragment<HomeViewModel, FragmentSystemsLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_systems_layout
    }

    override fun onClick(p0: View?) {
    }

    override fun initView() {
        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvSystem.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvSystem, LinearLayoutManager(App.mWindowsContext))
    }

    override fun observe() {
        mViewModel.systemList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                val list = mutableListOf<MainSystemBinder>()
                for (i in it.indices) {

                    val systemList = it[i].children
                    var tempList = mutableListOf<MainSystemItemBinder>()
                    // 遍历集合中的内容
                    for (j in systemList.indices) {
                        tempList.add(MainSystemItemBinder(systemList[j]))
                    }
                    if (tempList.size > 0) {
                        list.add(MainSystemBinder(tempList, it[i].name))
                    }
                }
                // 加载到list中
                add(MainSystemContainerBinder(list.map {
                    it.apply {
                        setOnClickListener(this@SystemFragment::onRecycleViewClick)
                    }
                }))
            })
        })
    }

    override fun initData() {
        mViewModel.loadSystemList()
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
    }
}