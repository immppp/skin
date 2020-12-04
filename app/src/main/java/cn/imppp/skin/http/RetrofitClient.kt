package cn.imppp.skin.http

import cn.imppp.netmodlelibrary.http.cookie.CookieJarImpl
import cn.imppp.netmodlelibrary.http.cookie.interceptor.BaseInterceptor
import cn.imppp.netmodlelibrary.http.cookie.interceptor.CacheInterceptor
import cn.imppp.netmodlelibrary.http.cookie.store.PersistentCookieStore
import cn.imppp.netmodlelibrary.http.interceptor.logging.Level
import cn.imppp.netmodlelibrary.http.interceptor.logging.Logger
import cn.imppp.netmodlelibrary.http.interceptor.logging.LoggingInterceptor
import cn.imppp.netmodlelibrary.utils.HttpsUtils
import cn.imppp.netmodlelibrary.utils.Utils
import cn.imppp.skin.BuildConfig
import cn.imppp.skin.base.App
import cn.imppp.skin.constant.Urls
import cn.imppp.skin.utils.MoshiHelper
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val DEFAULT_TIMEOUT = 20L
    private val mContext =
        Utils.getContext()
    /**
     * cookie
     */
    val cookieJar = CookieJarImpl(PersistentCookieStore(App.mWindowsContext))

    /**
     * sslParams
     */
    val sslParams: HttpsUtils.SSLParams = HttpsUtils.getSslSocketFactory()

    /**log**/
    private val logger = HttpLoggingInterceptor.Logger {
        Logger.DEFAULT
    }
    private val logInterceptor = HttpLoggingInterceptor(logger).apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    /**
     * okHttpClient
     */
    private val okHttpClient = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .addInterceptor(BaseInterceptor(null))
        .addInterceptor(CacheInterceptor(mContext!!))
        .sslSocketFactory(sslParams?.sSLSocketFactory, sslParams?.trustManager)
        .addNetworkInterceptor(
                    LoggingInterceptor.Builder() //构建者模式
                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response") // Response的Tag
                        .addHeader(
                            "log-header",
                            "log for request header."
                        ) // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
        )
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(Urls.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(MoshiHelper.moshi))
        .build()

    /**
     * apiService
     */
    val apiService = retrofit.create(ApiService::class.java)
}