package cn.imppp.skin.adapter.mainArticle

import android.annotation.SuppressLint
import android.text.TextUtils
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemQuestionLayoutBinding
import cn.imppp.skin.entity.QuestionEntity

class MainQuestionBinder(val data: QuestionEntity) : MultiTypeBinder<ItemQuestionLayoutBinding>() {

    override fun layoutId(): Int {
        return R.layout.item_question_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is QuestionEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemQuestionLayoutBinding) {
        if (!TextUtils.isEmpty(data.author)) {
            binding.tvQuestionAuthor.text = "作者：${data.author}"
        } else if (!TextUtils.isEmpty(data.shareUser)) {
            binding.tvQuestionAuthor.text = "分享人：${data.shareUser}"
        }

        binding.tvQuestionTime.text = "时间：${data.niceDate}"
    }
}