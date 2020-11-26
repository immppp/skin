package cn.imppp.multitype_adapter_library.callback

import androidx.recyclerview.widget.DiffUtil
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder

class DiffItemCallback<T : MultiTypeBinder<*>> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.layoutId() == newItem.layoutId()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hashCode() == newItem.hashCode() && oldItem.areContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}