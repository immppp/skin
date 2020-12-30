package cn.imppp.skin.ui.activity.project

import android.content.Intent
import android.util.Log
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
import cn.imppp.skin.adapter.project.ProjectBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.ActivityProjectLayoutBinding
import cn.imppp.skin.entity.ProjectEntity
import cn.imppp.skin.ui.activity.web.WebViewActivity
import kotlinx.android.synthetic.main.fragment_project_layout.*

class ProjectActivity : BaseActivity<ProjectViewModel, ActivityProjectLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<ProjectViewModel> {
        return ProjectViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_project_layout
    }

    override fun initData() {
        val intent = intent
        mViewModel.cid.value = intent.getIntExtra(Constant.cId, 100)
        Log.i("cid", "${mViewModel.cid.value}")

        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvProject.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvProject, LinearLayoutManager(App.mWindowsContext))
    }

    override fun observer() {
        mViewModel.projectList.observe(this, Observer {
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                for (i in it.indices) {
                    add(ProjectBinder(it[i]).apply {
                        setOnClickListener(this@ProjectActivity::onRecycleViewClick)
                    })
                }
            })
        })
    }

    override fun loadData() {
        mViewModel.loadProjectList(mViewModel.page.value!!, mViewModel.cid.value!!)
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        when (view.id) {
            R.id.cvProjectLayout -> {
                any as ProjectEntity
                val intent = Intent(App.mWindowsContext, WebViewActivity::class.java)
                intent.putExtra(Constant.url, any.link)
                startActivity(intent)
            }
        }
    }
}