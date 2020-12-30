package cn.imppp.skin.ui.fragment.home

import android.content.Intent
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
import cn.imppp.skin.adapter.mainArticle.MainSquareBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.MainFragmentLayoutBinding
import cn.imppp.skin.databinding.SquareFragmentLayoutBinding
import cn.imppp.skin.entity.SquareEntity
import cn.imppp.skin.ui.activity.web.WebViewActivity
import cn.imppp.skin.ui.fragment.main.HomeViewModel
import kotlinx.android.synthetic.main.main_fragment_layout.*
import kotlinx.android.synthetic.main.square_fragment_layout.*

class SquareFragment: BaseFragment<HomeViewModel, SquareFragmentLayoutBinding>(), OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.square_fragment_layout
    }

    override fun onClick(p0: View?) {
    }

    override fun initData() {
        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvSquare.layoutAnimation = controller
        mMultiTypeAdapter = createMultiTypeAdapter(rvSquare, LinearLayoutManager(App.mWindowsContext))
        mViewModel.loadSquareList(0)
    }

    override fun observe() {
        mViewModel.squareList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    add(MainSquareBinder(it[i]).apply {
                        setOnClickListener(this@SquareFragment::onRecycleViewClick)
                    })
                }

            })
        })
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        when(view.id) {
            R.id.cvSquareLayout -> {
                any as SquareEntity
                val intent = Intent(App.mWindowsContext, WebViewActivity::class.java)
                intent.putExtra(Constant.url, any.link)
                startActivity(intent)
            }
        }
    }
}