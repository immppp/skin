package cn.imppp.skin.base

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import cn.imppp.skin.BR
import cn.imppp.skin.R
import cn.imppp.skin.repository.LocalRepository
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.title_layout.*

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity(),
    View.OnClickListener {

    protected open lateinit var mBinding: B
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

    /**
     * 观察者列表
     */
    open fun observer() {}

    /**
     * 初始化数据
     */
    open fun initData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
//        QMUIStatusBarHelper.translucent(this, Color.TRANSPARENT)
//        QMUIStatusBarHelper.setStatusBarLightMode(this)
        initViewModel()
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.click, this)
        mBinding.setVariable(BR.vm, mViewModel)
        initData()
        loadData()
        observer()
        if(mViewModel.haveTitleLayout.value!!) {
            ivBack.setOnClickListener(this)
        } else {
            if (!mViewModel.backBottom.value!!) {
                ivBack.visibility = View.GONE
            }
        }
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBack -> finish()
        }
    }
}