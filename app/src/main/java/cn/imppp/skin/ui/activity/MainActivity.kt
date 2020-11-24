package cn.imppp.skin.ui.activity

import android.content.Intent
import android.util.Log
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.state.MainViewModel
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun loadData() {
        mViewModel.login()
        btNextPage.onClick { startActivity(Intent(this, FragmentActivity::class.java)) }
        btLight.onClick {
            mViewModel.login()
        }
    }

    private fun requestData() {
        // 内容会分配线程执行，不影响主线程内容
        GlobalScope.launch {
            // 请求token
            val token = getToken()
            val name = getName(token)
            setData(token, name)
        }
        Log.i("requestData", "执行接下来的操作${Thread.currentThread().id}")
    }

    private fun setData(token: String, name: String) {
        Log.i("requestData", "$token   $name   ${Thread.currentThread().id}")
    }

    // suspend修饰的内容按顺序执行
    private suspend fun getName(name: String): String {
        Log.i("requestData", "${Thread.currentThread().id}")
        delay(2000)
        return "name"
    }

    // suspend修饰的内容按顺序执行
    private suspend fun getToken(): String {
        Log.i("requestData", "${Thread.currentThread().id}")
        delay(2000)
        return "token"
    }

    private fun launchJob() {
        // launch不会阻断主线程。
        val job = GlobalScope.launch {
            delay(3000)
            Log.i("launchJob", "协程执行结束-- 线程id：${Thread.currentThread().id}")
        }
    }

    private fun runBlocking() {
        // runBlocking启动的协程任务会阻断当前线程，直到该协程执行结束。当协程执行结束之后，页面才会被显示出来。
        runBlocking {
            repeat(8) {
                Log.i("runBlocking", "协程执行的线程次数$it  线程id：${Thread.currentThread().id}")
                delay(1000)
            }
        }
    }

}
