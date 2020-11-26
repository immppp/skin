package cn.imppp.multitype_adapter_library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 创建一个adapter
 */
fun createMultiTypeAdapter(recyclerView: RecyclerView, layoutManager: RecyclerView.LayoutManager) : MultiTypeAdapter {
    recyclerView.layoutManager = layoutManager
    val mMultiTypeAdapter = MultiTypeAdapter(layoutManager)
    recyclerView.adapter = mMultiTypeAdapter
    // 处理recyclerView的毁掉
    recyclerView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(p0: View?) {
            mMultiTypeAdapter.onDetachedFromRecyclerView(recyclerView)
        }

        override fun onViewDetachedFromWindow(p0: View?) {
        }
    })
    return mMultiTypeAdapter
}

/**
 * 扩展函数，重载adapter类
 */
inline operator fun MultiTypeAdapter.invoke(block: MultiTypeAdapter.() -> Unit): MultiTypeAdapter {
    this.block()
    return this
}

fun <T : ViewDataBinding> ViewGroup.inflateDataBinding(layoutId: Int): T = DataBindingUtil.inflate(
    LayoutInflater.from(context), layoutId, this, false)!!