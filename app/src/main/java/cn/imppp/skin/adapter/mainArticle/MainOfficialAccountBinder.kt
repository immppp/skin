package cn.imppp.skin.adapter.mainArticle

import android.annotation.SuppressLint
import android.text.TextUtils
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemOfficialAccountLayoutBinding
import cn.imppp.skin.databinding.ItemSquareLayoutBinding
import cn.imppp.skin.entity.OfficialAccountEntity
import cn.imppp.skin.entity.SquareEntity

class MainOfficialAccountBinder(val data: OfficialAccountEntity) : MultiTypeBinder<ItemOfficialAccountLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_official_account_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is OfficialAccountEntity && other == data
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(binding: ItemOfficialAccountLayoutBinding) {
        if (data.userControlSetTop) {
            binding.clItemOfficialLayout.setBackgroundColor(binding.root.resources.getColor(R.color.theme))
        } else {
            binding.clItemOfficialLayout.setBackgroundColor(binding.root.resources.getColor(R.color.qmui_s_transparent))
        }
    }
}