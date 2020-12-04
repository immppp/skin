package cn.imppp.skin.ui.activity.login

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.RegisterEntity
import cn.imppp.skin.entity.UserEntity
import cn.imppp.skin.repository.NetRepository

class LoginViewModel : BaseViewModel() {

    val resultData = MutableLiveData<UserEntity>()

    val registerData = MutableLiveData<RegisterEntity>()

    fun login(
        userName: String,
        password: String
    ) {
        jobLiveData.value = launch(
            block = {
                resultData.value = NetRepository.getInstance().login(userName, password)
            },
            error = {
                Toast.makeText(App.mWindowsContext, "${it.message}", Toast.LENGTH_SHORT).show()
            },
            cancel = {

            }
        )
    }

    /**
     * 注册
     */
    fun register(
        userName: String,
        password: String
    ) {
        jobLiveData.value = launch(
            block = {
                registerData.value = NetRepository.getInstance().register(userName, password)
            },
            error = {
                Toast.makeText(App.mWindowsContext, "${it.message}", Toast.LENGTH_SHORT).show()
            },
            cancel = {

            }
        )
    }

}