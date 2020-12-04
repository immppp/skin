package cn.imppp.skin.ui.fragment.official

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
import cn.imppp.skin.adapter.mainArticle.MainOfficialAccountBinder
import cn.imppp.skin.adapter.mainArticle.MainQuestionBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentOfficialAccountLayoutBinding
import cn.imppp.skin.state.MainViewModel
import kotlinx.android.synthetic.main.fragment_official_account_layout.*
import kotlinx.android.synthetic.main.fragment_question_layout.*

class OfficialAccountFragment :
    BaseFragment<OfficialAccountViewModel, FragmentOfficialAccountLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<OfficialAccountViewModel> {
        return OfficialAccountViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_official_account_layout
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
        rvOfficialAccount.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvOfficialAccount, GridLayoutManager(App.mWindowsContext, 2))
    }

    override fun observe() {
        mViewModel.officialList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    add(MainOfficialAccountBinder(it[i]).apply {
                        setOnClickListener(this@OfficialAccountFragment::onRecycleViewClick)
                    })
                }
            })
        })
    }

    override fun initData() {
        mViewModel.loadOfficialList()
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        when(view.id) {
            R.id.clItemOfficialLayout-> {
                view.setBackgroundColor(resources.getColor(R.color.theme))
            }
        }
    }

}