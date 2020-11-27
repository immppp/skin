package cn.imppp.skin.ui.activity.single

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
import cn.imppp.skin.adapter.single.SingleBinder
import cn.imppp.skin.adapter.single.SingleContainerBinder
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.SingleRecyclerViewLayoutBinding
import cn.imppp.skin.entity.SingleEntity
import kotlinx.android.synthetic.main.single_recycler_view_layout.*

class SingleRecyclerViewActivity :
    BaseActivity<SingleRecyclerViewModel, SingleRecyclerViewLayoutBinding>(),
    OnViewClickListener {

    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun layoutRes(): Int {
        return R.layout.single_recycler_view_layout
    }

    override fun viewModelClass(): Class<SingleRecyclerViewModel> {
        return SingleRecyclerViewModel::class.java
    }

    override fun loadData() {
        // 设置加载动画
        controller = LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.animate))
        recyclerView.layoutAnimation = controller
        mMultiTypeAdapter = createMultiTypeAdapter(recyclerView, LinearLayoutManager(this))
        mViewModel.loadData()
    }

    override fun observer() {
        val userWriteFunction = 1       //  使用哪一种写法去实现列表展示
        mViewModel.data.observe(this, Observer {
            // 刷新数据
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                if (userWriteFunction == 0) {
                    // 多列表的时候建议使用该方法，因为会有一个列表承载者
                    val list = mutableListOf<SingleBinder>()
                    for (i in it.indices) {
                        list.add(SingleBinder(it[i]))
                    }
                    add(SingleContainerBinder(list.map {
                        it.apply {
                            setOnClickListener(this@SingleRecyclerViewActivity::onRecycleViewClick)
                        }
                    }))
                } else {
                    // 单列表使用该方法，简便快捷
                    for (i in it.indices) {
                        add(SingleBinder(it[i]).apply {
                            setOnClickListener(this@SingleRecyclerViewActivity::onRecycleViewClick)
                        })
                    }
                }
            })
        })
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        when (view.id) {
            R.id.tvSingleName -> {
                any as SingleBinder
                Log.i("singlePage", "${any.data}")
            }
            R.id.ivSingleBackGround -> {
                any as SingleBinder
                val newData = any.data
                if (newData.picture.toInt() > 8) {
                    newData.picture = "5"
                } else {
                    newData.picture = "10"
                }
                // 刷新数据
                val data: MutableList<SingleEntity>? = mViewModel.data.value
                data?.set(data.indexOf(any.data), newData)
                mViewModel.data.value = data
            }
        }
    }
}