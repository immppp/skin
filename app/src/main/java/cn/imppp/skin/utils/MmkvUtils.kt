package cn.imppp.skin.utils

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * mmkv的使用初始化类
 */

class MmkvUtils {
    init {
        mv = MMKV.defaultMMKV()
    }

    companion object {
        private var mInstance: MmkvUtils? = null
        private var mv: MMKV? = null
        /**
         * 初始化MMKV,只需要初始化一次，建议在Application中初始化
         *
         */
        val instance: MmkvUtils?
            get() {
                if (mInstance == null) {
                    synchronized(MmkvUtils::class.java) {
                        if (mInstance == null) {
                            mInstance = MmkvUtils()
                        }
                    }
                }
                return mInstance
            }

        /**
         * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
         *
         * @param key
         * @param object
         */
        fun encode(key: String?, `object`: Any) {
            if (`object` is String) {
                mv!!.encode(key, `object`)
            } else if (`object` is Int) {
                mv!!.encode(key, `object`)
            } else if (`object` is Boolean) {
                mv!!.encode(key, `object`)
            } else if (`object` is Float) {
                mv!!.encode(key, `object`)
            } else if (`object` is Long) {
                mv!!.encode(key, `object`)
            } else if (`object` is Double) {
                mv!!.encode(key, `object`)
            } else if (`object` is ByteArray) {
                mv!!.encode(key, `object`)
            } else {
                mv!!.encode(key, `object`.toString())
            }
        }

        fun encodeSet(
            key: String?,
            sets: Set<String?>?
        ) {
            mv!!.encode(key, sets)
        }

        fun encodeParcelable(key: String?, obj: Parcelable?) {
            mv!!.encode(key, obj)
        }

        fun decodeInt(key: String?): Int {
            return mv!!.decodeInt(key, 0)
        }

        fun decodeDouble(key: String?): Double {
            return mv!!.decodeDouble(key, 0.00)
        }

        fun decodeLong(key: String?): Long {
            return mv!!.decodeLong(key, 0L)
        }

        fun decodeBoolean(key: String?): Boolean {
            return mv!!.decodeBool(key, false)
        }

        fun decodeFloat(key: String?): Float {
            return mv!!.decodeFloat(key, 0f)
        }

        fun decodeBytes(key: String?): ByteArray {
            return mv!!.decodeBytes(key)
        }

        fun decodeString(key: String?): String {
            return mv!!.decodeString(key, "")
        }

        fun decodeStringSet(key: String?): Set<String> {
            return mv!!.decodeStringSet(
                key,
                Collections.emptySet<String?>()
            )
        }

        fun decodeParcelable(key: String?): Parcelable {
            return mv!!.decodeParcelable(key, null)
        }

        /**
         * 移除某个key对
         *
         * @param key
         */
        fun removeKey(key: String?) {
            mv!!.removeValueForKey(key)
        }

        /**
         * 清除所有key
         */
        fun clearAll() {
            mv!!.clearAll()
        }
    }
}
