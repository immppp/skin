package cn.imppp.multitype_adapter_library.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder

/**
 * recycler的viewHolder
 */

class MultiTypeViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root), AutoCloseable {

    private var mAlreadyBinding: MultiTypeBinder<ViewDataBinding>? = null

    /**
     * 绑定binder
     */
    fun onBindViewHolder(items: MultiTypeBinder<ViewDataBinding>) {
        // 如果两次绑定的binder不一致，则直接销毁
        if (mAlreadyBinding != null && items !== mAlreadyBinding)
            close()
        // 开始绑定
        items.bindViewDataBinding(binding)
        // 保存绑定的binder
        mAlreadyBinding =  items
    }

    override fun close() {
    }
}