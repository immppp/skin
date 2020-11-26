package cn.imppp.multitype_adapter_library.binder

import android.view.View
import androidx.viewbinding.BuildConfig
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import java.lang.NullPointerException

/**
 * 点击事件
 */

open class ClickBinder : OnViewClickListener {

    protected open var mOnclickListener: ((view: View, any: Any?) -> Unit)? = null

    protected open var mOnLongClickListener: ((view: View, any: Any?) -> Unit)? = null

    /**
     * 设置view的点击事件
     */
    open fun setOnClickListener(listener: (view: View, any: Any?) -> Unit): ClickBinder {
        this.mOnclickListener = listener
        return this
    }

    /**
     * 设置view的长按点击事件
     */
    open fun setOnLongClickListener(listener: (view: View, any: Any?) -> Unit): ClickBinder {
        this.mOnLongClickListener = listener
        return this
    }

    override fun onRecycleViewClick(view: View) {
        onRecycleViewClick(view, this)
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        if (mOnclickListener != null) {
            mOnclickListener?.invoke(view, any)
        } else {
            if (BuildConfig.DEBUG) throw NullPointerException("OnClick未绑定")
        }
    }

    override fun onRecycleViewLongClick(view: View) {
        onRecycleViewLongClick(view, this)
    }

    override fun onRecycleViewLongClick(view: View, any: Any?) {
        if (mOnLongClickListener != null) {
            mOnLongClickListener?.invoke(view, any)
        } else {
            if (BuildConfig.DEBUG) throw NullPointerException("OnLongClick未绑定")
        }
    }
}