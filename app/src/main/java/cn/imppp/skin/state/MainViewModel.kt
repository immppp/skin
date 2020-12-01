package cn.imppp.skin.state

import android.util.Log
import androidx.datastore.preferences.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.UserEntity
import cn.imppp.skin.repository.ProtoDataStoreRepository
import cn.imppp.skin.repository.NetRepository
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    val userLiveData = MutableLiveData<UserEntity>()

    fun login() {
        jobLiveData.value = launch(
            block = {
                Log.i("ThreadTag", "mainViewModel${Thread.currentThread().id}")
                // 开始请求
                userLiveData.value = NetRepository.getInstance().login()
                Log.i("ThreadTag", " 返回的数据结果为：$userLiveData.value")
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