package cn.imppp.skin.adapter.single

import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemSingleBinding
import cn.imppp.skin.databinding.ItemSingleContainerBinding
import cn.imppp.skin.entity.SingleEntity

class SingleBinder(val data: SingleEntity) : MultiTypeBinder<ItemSingleBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_single
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is SingleBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemSingleBinding) {
        if (data.picture.toInt() > 5) {
            binding.ivSingleBackGround.background = binding.root.context.getDrawable(R.color.qmui_config_color_blue)
        } else {
            binding.ivSingleBackGround.background = binding.root.context.getDrawable(R.color.qmui_config_color_red)
        }
    }

}

class SingleContainerBinder(val data: List<SingleBinder>) :
    MultiTypeBinder<ItemSingleContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_single_container
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is SingleContainerBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemSingleContainerBinding) {
        (createMultiTypeAdapter(binding.recyclerView, LinearLayoutManager(binding.root.context))) {
            notifyAdapterChanged(data)
        }
    }
}