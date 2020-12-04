package cn.imppp.skin.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.imppp.skin.BR

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment(), View.OnClickListener {
    protected lateinit var mViewModel: VM
    protected open lateinit var mBinding: B


    private var lazyLoad = false

    open fun layoutRes() = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initDataBinding(inflater, container).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        mBinding.setVariable(BR.vm, mViewModel)
        observe()
        initView()
    }

    override fun onResume() {
        super.onResume()
        if (lazyLoad) {
            lazyLoadData()
        } else {
            initData()
        }
        lazyLoad = true
    }

    override fun onStop() {
        super.onStop()
        Log.i("road", "baseFragment onStop")
        // 取消正在加载的请求
        mViewModel.cancelJob(mViewModel.jobLiveData.value)
    }

    /**
     * 创建DataBinding
     */
    private fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): B {
        mBinding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        mBinding.lifecycleOwner = this
        mBinding.setVariable(BR.click, this)
        return mBinding
    }

    /**
     * 创建viewModel
     */
    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>

    open fun observe() {

    }

    open fun initView() {

    }

    open fun initData() {

    }

    open fun lazyLoadData() {

    }
}