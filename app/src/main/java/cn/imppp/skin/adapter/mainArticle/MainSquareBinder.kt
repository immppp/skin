package cn.imppp.skin.adapter.mainArticle

import android.annotation.SuppressLint
import android.text.TextUtils
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemSquareLayoutBinding
import cn.imppp.skin.entity.SquareEntity

class MainSquareBinder(val data: SquareEntity) : MultiTypeBinder<ItemSquareLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_square_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is SquareEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemSquareLayoutBinding) {
        if (!TextUtils.isEmpty(data.author)) {
            binding.tvSquareAuthor.text = "作者：${data.author}"
        } else if (!TextUtils.isEmpty(data.shareUser)) {
            binding.tvSquareAuthor.text = "分享人：${data.shareUser}"
        }

        binding.tvSquareTime.text = "时间：${data.niceDate}"
    }
}