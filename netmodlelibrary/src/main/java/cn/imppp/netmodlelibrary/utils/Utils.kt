package cn.imppp.netmodlelibrary.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

class Utils private constructor() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        /**
         * 初始化工具类
         *
         * @param context 上下文
         */
        fun init(context: Context) {
            Companion.context = context.applicationContext
        }

        /**
         * 获取ApplicationContext
         *
         * @return ApplicationContext
         */
        fun getContext(): Context? {
            Log.i("执行该方法", "getConText")
            if (context != null) {
                return context
            }
            throw NullPointerException("should be initialized in application")
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}