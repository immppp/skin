package cn.imppp.multitype_adapter_library.callback

import android.view.View

interface OnViewClickListener {

    fun onRecycleViewClick(view: View) {
        onRecycleViewClick(view, null)
    }

    fun onRecycleViewClick(view: View, any: Any?) {

    }

    fun onRecycleViewLongClick(view: View) {
        onRecycleViewLongClick(view, null)
    }

    fun onRecycleViewLongClick(view: View, any: Any?) {

    }
}