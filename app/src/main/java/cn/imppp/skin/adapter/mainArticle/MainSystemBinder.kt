package cn.imppp.skin.adapter.mainArticle

import android.annotation.SuppressLint
import android.text.TextUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemArticleLayoutBinding
import cn.imppp.skin.databinding.ItemSystemContainerLayoutBinding
import cn.imppp.skin.databinding.ItemSystemItemLayoutBinding
import cn.imppp.skin.databinding.ItemSystemLayoutBinding
import cn.imppp.skin.entity.ArticleEntity
import cn.imppp.skin.entity.BaseSystemEntity
import cn.imppp.skin.entity.SystemEntity

// 主list集合
class MainSystemContainerBinder(val data: List<MainSystemBinder>) :
    MultiTypeBinder<ItemSystemContainerLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_system_container_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is MainSystemContainerBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemSystemContainerLayoutBinding) {
        (createMultiTypeAdapter(
            binding.rvSystemContainer,
            LinearLayoutManager(binding.root.context)
        )) {
            notifyAdapterChanged(data)
        }
    }
}

// 副list集合
class MainSystemBinder(val data: List<MainSystemItemBinder>, val title: String) :
    MultiTypeBinder<ItemSystemLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_system_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is MainSystemBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemSystemLayoutBinding) {
        binding.tvSystemTitle.text = title
        (createMultiTypeAdapter(
            binding.rvSystem,
            GridLayoutManager(binding.root.context, 2)
        )) {
            notifyAdapterChanged(data)
        }
    }
}

class MainSystemItemBinder(val data: SystemEntity) :
    MultiTypeBinder<ItemSystemItemLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_system_item_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is BaseSystemEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemSystemItemLayoutBinding) {
    }
}