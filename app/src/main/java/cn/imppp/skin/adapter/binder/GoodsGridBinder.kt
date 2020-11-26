package cn.imppp.skin.adapter.binder

import androidx.recyclerview.widget.GridLayoutManager
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemGoodsBinding
import cn.imppp.skin.databinding.ItemGoodsContainerBinding

class GoodsGridContainerBinder(val data: List<GoodsBinder>) : MultiTypeBinder<ItemGoodsContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_goods_container
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is GoodsGridContainerBinder && other.data == data
    }

    override fun onBindViewHolder(binding: ItemGoodsContainerBinding) {
        (createMultiTypeAdapter(binding.goodsRecyclerView, GridLayoutManager(binding.root.context, 2))) {
            notifyAdapterChanged(data)
        }
    }
}

class GoodsBinder(val index: String) : MultiTypeBinder<ItemGoodsBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_goods
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is GoodsBinder && other.index == index
    }

}