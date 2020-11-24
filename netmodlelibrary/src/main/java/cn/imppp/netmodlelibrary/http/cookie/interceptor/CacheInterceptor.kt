package cn.imppp.netmodlelibrary.http.cookie.interceptor

import android.content.Context
import cn.imppp.netmodlelibrary.http.cookie.NetworkUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CacheInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        return if (NetworkUtil.isNetworkAvailable(context)) {
            val response = chain.proceed(request)
            // read from cache for 60 s
            val maxAge = 60
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else { //读取缓存信息
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            val response = chain.proceed(request)
            //set cache times is 3 days
            val maxStale = 60 * 60 * 24 * 3
            response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
    }

}