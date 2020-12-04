package cn.imppp.netmodlelibrary.http.cookie

import android.util.Log
import cn.imppp.netmodlelibrary.http.cookie.store.CookieStore
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookieJarImpl(cookieStore: CookieStore?) : CookieJar {
    val cookieStore: CookieStore

    @Synchronized
    override fun saveFromResponse(
        url: HttpUrl,
        cookies: List<Cookie>
    ) {
        Log.i("CookieJar", "save cookie $url   \n  $cookies")
        cookieStore.saveCookie(url, cookies)
    }

    @Synchronized
    override fun loadForRequest(url: HttpUrl): List<Cookie?>? {
        return cookieStore.loadCookie(url)
    }

    init {
        requireNotNull(cookieStore) { "cookieStore can not be null!" }
        this.cookieStore = cookieStore
    }
}