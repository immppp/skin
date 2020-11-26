package cn.imppp.skin.adapter.binder

import androidx.recyclerview.widget.GridLayoutManager
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemCategoryChildBinding
import cn.imppp.skin.databinding.ItemCategoryContainerBinding

class CategoryContainerBinder(val category: List<CategoryItemBinder>) : MultiTypeBinder<ItemCategoryContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_category_container
    }

    override fun onBindViewHolder(binding: ItemCategoryContainerBinding) {
        (createMultiTypeAdapter(binding.categoryRecycler, GridLayoutManager(binding.root.context, 5))) {
            notifyAdapterChanged(category)
        }
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is CategoryContainerBinder && other.category == category
    }

}

// 单个条目的数据内容
class CategoryItemBinder(val title: String): MultiTypeBinder<ItemCategoryChildBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_category_child
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is CategoryItemBinder && other.title == title
    }

}