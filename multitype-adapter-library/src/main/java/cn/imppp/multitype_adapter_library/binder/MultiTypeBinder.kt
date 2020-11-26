package cn.imppp.multitype_adapter_library.binder

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import cn.imppp.multitype_adapter_library.BR
import cn.imppp.multitype_adapter_library.R

/**
 * 数据绑定抽象类
 */

abstract class MultiTypeBinder<V : ViewDataBinding> : ClickBinder() {

    /**
     * BR.data
     */
    protected open val variableId = BR.data

    /**
     * 被绑定的viewDataBinding
     */
    open var binding: V? = null

    /**
     * 给绑定的view设置tag
     */
    protected var bindingViewVersion = (0L until Long.MAX_VALUE).random()

    /**
     * 返回layoutId,供Adapter使用
     */
    @LayoutRes
    abstract fun layoutId(): Int

    /**
     * 两次更新的binder内容是否相同
     */
    abstract fun areContentsTheSame(other : Any): Boolean

    /**
     * 绑定viewDataBinding
     */
    fun bindViewDataBinding(binding: V) {
        // 如果两次数据相同则不做绑定
        if (this.binding === binding && binding.root.getTag(R.id.bindingVersion) == bindingViewVersion)
            return
        binding.root.setTag(R.id.bindingVersion, ++bindingViewVersion)
        onUnBindViewHolder()
        this.binding = binding
        binding.setVariable(variableId, this)
        // 给bingding绑定生命周期，方便观察livedata的值，今儿更新UI。如果不绑定，liveData的值改变时，ui不会更新
        if (binding.root.context is LifecycleOwner) {
            binding.lifecycleOwner = binding.root.context as LifecycleOwner
        } else {
            binding.lifecycleOwner = AlwaysActiveLifecycleOwner()
        }
        onBindViewHolder(binding)
        // 即使更新绑定数据的view
        binding.executePendingBindings()
    }

    /**
     * 解除绑定
     */
    fun unBindViewHolder() {
        if (this.binding != null) {
            onUnBindViewHolder()
            this.binding = null
        }
    }

    /**
     * 绑定后对View的一些操作，如赋值、修改属性
     */
    protected open fun onBindViewHolder(binding: V) {}

    /**
     * 解绑操作
     */
    protected open fun onUnBindViewHolder() {}

    /**
     * 为 Binder 绑定生命周期，在 {@link Lifecycle.Event#ON_RESUME} 时响应
     */
    internal class AlwaysActiveLifecycleOwner : LifecycleOwner {

        override fun getLifecycle(): Lifecycle = object : LifecycleRegistry(this) {
            init {
                handleLifecycleEvent(Event.ON_RESUME)
            }
        }
    }
}