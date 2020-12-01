package cn.imppp.skin.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 实现数据的序列化方式 Parcelize
 */

@Parcelize
data class ParcelizeEntity(var name: String) : Parcelable