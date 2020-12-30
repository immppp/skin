package cn.imppp.skin.repository.local

import cn.imppp.skin.theme.Theme
import cn.imppp.skin.utils.SPUtils

/**
 * 本地持久化数据缓存区
 */

class LocalRepository {

    companion object {
        private val localRepository: LocalRepository =
            LocalRepository()
        fun getInstance(): LocalRepository =
            localRepository
    }

    // 缓存当前的主题（黑色/白色主题）
    fun saveCurrentTheme(theme: Int) {
        SPUtils.getInstance().put("Theme", theme)
    }

    // 获取主题
    fun getCurrentTheme(): Int {
        return SPUtils.getInstance().getInt("Theme", Theme.DARK.mode)
    }
}