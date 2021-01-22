package cn.imppp.skin.adapter.demo

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemDemoLayoutBinding
import cn.imppp.skin.databinding.ItemProjectLayoutBinding
import cn.imppp.skin.entity.ProjectEntity
import com.bumptech.glide.Glide

class DemoBinder(val data : String): MultiTypeBinder<ItemDemoLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_demo_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is String && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemDemoLayoutBinding) {
        binding.tvProjectTime.text = data
    }
}