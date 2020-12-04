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
import cn.imppp.skin.adapter.mainArticle.MainArticleBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.MainFragmentLayoutBinding
import cn.imppp.skin.ui.fragment.main.HomeViewModel
import kotlinx.android.synthetic.main.main_fragment_layout.*

class MainFragment: BaseFragment<HomeViewModel, MainFragmentLayoutBinding>() ,
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.main_fragment_layout
    }

    override fun initView() {
        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvMainFragment.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvMainFragment, LinearLayoutManager(App.mWindowsContext))
    }

    override fun initData() {
        mViewModel.loadArticleList(0)
    }

    override fun observe() {
        mViewModel.articleList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    add(MainArticleBinder(it[i]).apply {
                        setOnClickListener(this@MainFragment::onRecycleViewClick)
                    })
                }

            })
        })
    }

    override fun lazyLoadData() {
    }

    override fun onClick(p0: View?) {
    }

    override fun onRecycleViewClick(view: View) {
    }
}