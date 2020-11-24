package cn.imppp.skin.state

import android.util.Log
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.repository.NetRepository
import kotlinx.coroutines.delay

class MainViewModel : BaseViewModel() {

    fun login() {
        jobLiveData.value = launch(
            block = {
                Log.i("ThreadTag", "mainViewModel${Thread.currentThread().id}")
//                delay(3000)
                // 开始请求
                val userEntity = NetRepository.getInstance().login()
                Log.i("ThreadTag", " 返回的数据结果为：$userEntity")
            },
            error = {
                Log.i("ThreadTag", "error mainViewModel${Thread.currentThread().id}")
                Log.i("requestData", "error 返回的数据结果为")
            },
            cancel = {
                Log.i("ThreadTag", "cancel mainViewModel${Thread.currentThread().id}")
                Log.i("requestData", "cancel 返回的数据结果为：${it.message}")
            }
        )
    }

}