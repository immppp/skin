package cn.imppp.multitype_adapter_library

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.callback.DiffItemCallback
import cn.imppp.multitype_adapter_library.holder.MultiTypeViewHolder
import java.lang.Exception
import java.lang.NullPointerException

/**
 * 扩展layoutManage
 */

class MultiTypeAdapter constructor(val layoutManager: RecyclerView.LayoutManager): RecyclerView.Adapter<MultiTypeViewHolder>() {

    // 使用后台线程通过差异性计算来更新列表
    private val mAsyncListChange by lazy { AsyncListDiffer(this, DiffItemCallback<MultiTypeBinder<*>>()) }

    // 存储MultiTypeBinder和holder
    private var mHashCodeViewType = LinkedHashMap<Int, MultiTypeBinder<*>>()

    init {
        setHasStableIds(true)
    }

    // 刷新列表
    fun notifyAdapterChanged(binders: List<MultiTypeBinder<*>>) {
        mHashCodeViewType = LinkedHashMap()
        binders.forEach {
            mHashCodeViewType[it.hashCode()] = it
        }
        mAsyncListChange.submitList(mHashCodeViewType.map { it.value })
    }

    // 刷新单个数据
    fun notifyAdapterChanged(binders: MultiTypeBinder<*>) {
        mHashCodeViewType = LinkedHashMap()
        mHashCodeViewType[binders.hashCode()] = binders
        mAsyncListChange.submitList(mHashCodeViewType.map { it.value })
    }

    override fun getItemViewType(position: Int): Int {
        val mItemBinder = mAsyncListChange.currentList[position]
        val mHashCode = mItemBinder.hashCode()
        if (!mHashCodeViewType.containsKey(mHashCode)) {
            mHashCodeViewType[mHashCode] = mItemBinder
        }
        return mHashCode
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder {
        try {
            return MultiTypeViewHolder(parent.inflateDataBinding(mHashCodeViewType[viewType]?.layoutId()!!))
        } catch (e: Exception) {
            throw NullPointerException("不存在${mHashCodeViewType[viewType]}类型的ViewHolder!")
        }
    }

    override fun getItemCount(): Int {
        return mAsyncListChange.currentList.size
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        val mCurrentBinder = mAsyncListChange.currentList[position] as MultiTypeBinder<ViewDataBinding>
        holder.itemView.tag = mCurrentBinder.layoutId()
        holder.onBindViewHolder(mCurrentBinder)
    }
}