package cn.imppp.skin.adapter.mainArticle

import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.skin.R
import cn.imppp.skin.databinding.ItemProjectTypeLayoutBinding
import cn.imppp.skin.entity.ArticleEntity
import cn.imppp.skin.entity.ProjectTypeEntity

class MainProjectBinder(val data: ProjectTypeEntity) : MultiTypeBinder<ItemProjectTypeLayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.item_project_type_layout
    }

    override fun areContentsTheSame(other: Any): Boolean {
        return other is ArticleEntity && other == data
    }

//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(binding: ItemProjectTypeLayoutBinding) {
//    }
}