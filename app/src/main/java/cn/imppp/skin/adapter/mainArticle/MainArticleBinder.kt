package cn.imppp.skin.adapter.mainArticle

import android.annotation.SuppressLint
import android.text.TextUtils
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemArticleLayoutBinding
import cn.imppp.skin.entity.ArticleEntity

class MainArticleBinder(val data: ArticleEntity) : MultiTypeBinder<ItemArticleLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_article_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is ArticleEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemArticleLayoutBinding) {
        if (!TextUtils.isEmpty(data.author)) {
            binding.tvArticleAuthor.text = "作者：${data.author}"
        } else if (!TextUtils.isEmpty(data.shareUser)) {
            binding.tvArticleAuthor.text = "分享人：${data.shareUser}"
        }

        binding.tvArticleTime.text = "时间：${data.niceDate}"
    }
}