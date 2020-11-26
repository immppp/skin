package cn.imppp.skin.adapter.binder

import androidx.recyclerview.widget.GridLayoutManager
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.multitype_adapter_library.invoke
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemRecommendContainerBinding
import cn.imppp.skin.databinding.ItemRecommendGoodsBinding

class RecommendContainerBinder(val goods: List<RecommendGoodsBinder>) :
    MultiTypeBinder<ItemRecommendContainerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_recommend_container
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is RecommendContainerBinder && other.goods == goods
    }

    override fun onBindViewHolder(binding: ItemRecommendContainerBinding) {
//        binding.recommendGoodsRecycler.addItemDecoration(Grid)
        (createMultiTypeAdapter(
            binding.recommendGoodsRecycler,
            GridLayoutManager(binding.root.context, 4)
        )) {
            notifyAdapterChanged(goods)
        }
    }

}

class RecommendGoodsBinder : MultiTypeBinder<ItemRecommendGoodsBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_recommend_goods
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is RecommendGoodsBinder
    }
}