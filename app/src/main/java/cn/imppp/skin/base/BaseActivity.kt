package cn.imppp.skin.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.imppp.skin.repository.LocalRepository

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {

    protected open lateinit var mViewModel: VM

    /**
     * 加载的布局
     */
    open fun layoutRes() = 0

    /**
     * 获取ViewModel的class
     */
    protected abstract fun viewModelClass(): Class<VM>

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    /**
     * 获取数据
     */
    open fun loadData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        initViewModel()
        loadData()
    }

    override fun onStart() {
        Log.i("BaseActivity", "${this.localClassName} onStart  ${delegate.localNightMode}")
        super.onStart()
    }

    override fun onPause() {
        Log.i("BaseActivity", "${this.localClassName} onPause  ${delegate.localNightMode}")
        super.onPause()
    }

    override fun onStop() {
        Log.i("BaseActivity", "${this.localClassName} onStop  ${delegate.localNightMode}")
        super.onStop()
        // 取消正在加载的请求
        mViewModel.cancelJob(mViewModel.jobLiveData.value)
    }

    override fun onDestroy() {
        Log.i("BaseActivity", "${this.localClassName} onDestroy  ${delegate.localNightMode}")
        super.onDestroy()
    }

    fun changeMode() {
        if (LocalRepository.getInstance().getCurrentTheme() != App.app.mCurrentThemes) {
            Log.i("BaseActivity", "变更状态")
            delegate.localNightMode = LocalRepository.getInstance().getCurrentTheme()
            App.app.mCurrentThemes = LocalRepository.getInstance().getCurrentTheme()
        }
    }

}