package cn.imppp.skin.ui.fragment.home

import android.content.Intent
import android.util.Log
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
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.MainFragmentLayoutBinding
import cn.imppp.skin.entity.ArticleEntity
import cn.imppp.skin.ui.activity.web.WebViewActivity
import cn.imppp.skin.ui.fragment.main.HomeViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import kotlinx.android.synthetic.main.main_fragment_layout.*

class MainFragment : BaseFragment<HomeViewModel, MainFragmentLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController
    private var currentPage = 0
    private var size = 0

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
        initSmartRefresh(srlMainLayout)
    }

    override fun initData() {
    }

    override fun observe() {
        mViewModel.articleList.observe(this, Observer {
            srlMainLayout.finishLoadMore()
            srlMainLayout.finishRefresh()
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    Log.i("mainFragment", it[i].title)
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

    override fun onRecycleViewClick(view: View, any: Any?) {
        when (view.id) {
            R.id.cv_article_layout -> {
                any as ArticleEntity
                val intent = Intent(App.mWindowsContext, WebViewActivity::class.java)
                intent.putExtra(Constant.url, any.link)
                startActivity(intent)
            }
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        size = mViewModel.articleList.value!!.size
        Log.i("mainFragment", "more: $refreshLayout   $size")
        currentPage++
        mViewModel.loadArticleList(currentPage)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        Log.i("mainFragment", "fresh: $refreshLayout")
        currentPage = 0
        mViewModel.loadArticleList(currentPage)
    }
}