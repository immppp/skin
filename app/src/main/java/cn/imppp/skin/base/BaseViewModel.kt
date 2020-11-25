package cn.imppp.skin.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.imppp.skin.http.ApiException
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import kotlinx.coroutines.Job
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

typealias Block<T> = suspend (CoroutineScope) -> T
typealias Error = suspend (Exception) -> Unit
typealias Cancel = suspend (Exception) -> Unit

open class BaseViewModel : ViewModel() {
    /**
     * 是否显示返回按钮
     */
    val backBottom = MutableLiveData<Boolean>(true)

    /**
     * 当前请求的job
     */
    val jobLiveData = MutableLiveData<Job>()

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @param cancel 取消时只需
     * @param showErrorToast 是否弹出错误吐司
     * @return Job
     */
    protected fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        showErrorToast: Boolean = true
    ): Job {
        Log.i("ThreadTag", "launch mainViewModel${Thread.currentThread().id}")
        jobLiveData.value = viewModelScope.launch {
            try {
                Log.i("ThreadTag", "block ${Thread.currentThread().id}")
                block.invoke(this)
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        onError(e, showErrorToast)
                        error?.invoke(e)
                    }
                }
            }
        }
        return jobLiveData.value!!
    }

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: Block<T>): Deferred<T> {
        return viewModelScope.async { block.invoke(this) }
    }

    /**
     * 取消协程
     * @param job 协程job
     */
    fun cancelJob(job: Job?) {
        if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
    }

    /**
     * 统一处理错误
     * @param e 异常
     * @param showErrorToast 是否显示错误吐司
     */
    private fun onError(e: Exception, showErrorToast: Boolean) {
        when (e) {
            is ApiException -> {
                when (e.code) {
                    -1001 -> {
                        // 登录失效，清除用户信息、清除cookie/token
                        Log.i("requestError", "登录失效，清除用户信息、清除cookie/token")
                    }
                    // 其他api错误
                    -1 -> if (showErrorToast)   Log.i("requestError", "其他api错误")
                    // 其他错误
                    else -> if (showErrorToast) Log.i("requestError", "其他错误1")
                }
            }
            // 网络请求失败
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException,
            is HttpException,
            is SSLHandshakeException ->
                if (showErrorToast) Log.i("requestError", "SSLHandshakeException")
            // 数据解析错误
            is JsonDataException, is JsonEncodingException ->
                if (showErrorToast) Log.i("requestError", "JsonDataException")
            // 其他错误
            else ->
                if (showErrorToast) Log.i("requestError", "其他错误")
        }
    }

}