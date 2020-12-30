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
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.main_fragment_layout.*

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment(), View.OnClickListener,
    OnRefreshLoadMoreListener {
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

    // smartRefresh初始化构造器
    open fun initSmartRefresh(layout: SmartRefreshLayout) {
        layout.setRefreshHeader(BaseRefreshHeader(App.mWindowsContext))
        layout.setRefreshFooter(BaseRefreshFooter(App.mWindowsContext, "请稍等"))
//        layout.setEnableScrollContentWhenLoaded(true) //是否在加载完成时滚动列表显示新的内容
//        layout.setEnableScrollContentWhenRefreshed(false)
        layout.setOnRefreshLoadMoreListener(this)
        layout.setEnableLoadMoreWhenContentNotFull(false) //是否在列表不满一页时候开启上拉加载功能
        layout.autoRefresh() //自动刷新
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
    }
}