package cn.imppp.skin.adapter.binder

import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemTopBannerBinding

class TopBinder : MultiTypeBinder<ItemTopBannerBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_top_banner
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is TopBinder
    }
}