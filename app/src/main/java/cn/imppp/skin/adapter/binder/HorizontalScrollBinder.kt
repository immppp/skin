package cn.imppp.skin.adapter.binder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemHorizontalContainerBinding
import cn.imppp.skin.databinding.ItemHorizontalTextBinding

class HorizontalScrollBinder(val data: List<HorizontalItemBinder>): MultiTypeBinder<ItemHorizontalContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_horizontal_container
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is HorizontalScrollBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemHorizontalContainerBinding) {
        (createMultiTypeAdapter(binding.multiTypeScrollRecycler, LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false))) {
            notifyAdapterChanged(data)
        }
    }
}

class HorizontalItemBinder(val index: String): MultiTypeBinder<ItemHorizontalTextBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_horizontal_text
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is HorizontalItemBinder
    }

}