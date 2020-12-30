package cn.imppp.skin.adapter.project

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemProjectLayoutBinding
import cn.imppp.skin.entity.ProjectEntity
import com.bumptech.glide.Glide

class ProjectBinder(val data : ProjectEntity): MultiTypeBinder<ItemProjectLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_project_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is ProjectEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemProjectLayoutBinding) {

        if (data.envelopePic.contains("https")) {
            Glide.with(binding.root).load(data.envelopePic).placeholder(R.drawable.icon_login).into(binding.ivProjectPic)
        } else {
            val url = data.envelopePic.replace("http", "https")
            Glide.with(binding.root).load(url).placeholder(R.drawable.icon_login).into(binding.ivProjectPic)
        }
        if (!TextUtils.isEmpty(data.author)) {
            binding.tvProjectAuthor.text = data.author
        } else if (!TextUtils.isEmpty(data.shareUser)) {
            binding.tvProjectAuthor.text = data.shareUser
        }

        binding.tvProjectTime.text = data.niceDate
    }
}