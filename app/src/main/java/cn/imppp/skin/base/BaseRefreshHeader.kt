package cn.imppp.skin.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import cn.imppp.skin.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle

/**
 * refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
 *         refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
 *
 *         refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
 *         refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）
 *         refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）
 *         refreshLayout.setFooterHeightPx(100);//同上-像素为单位 （V1.1.0删除）
 *
 *         refreshLayout.setFooterHeaderInsetStart(0);//设置 Header 起始位置偏移量 1.0.5
 *         refreshLayout.setFooterHeaderInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
 *         refreshLayout.setFooterFooterInsetStart(0);//设置 Footer 起始位置偏移量 1.0.5
 *         refreshLayout.setFooterFooterInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
 *
 *         refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
 *         refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
 *         refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
 *         refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4
 *
 *         refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
 *         refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
 *         refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
 *         refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
 *         refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
 *         refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
 *         refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
 *         refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
 *         refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
 *         refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
 *         refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
 *         refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
 *
 *         refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5
 *         refreshLayout.srlEnableClipHeaderWhenFixedBehind(true);//是否剪裁Header当时样式为FixedBehind时1.0.5
 *         refreshLayout.srlEnableClipFooterWhenFixedBehind(true);//是否剪裁Footer当时样式为FixedBehind时1.0.5
 *
 *         refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
 *         refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
 *
 *         refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener());//设置多功能监听器
 *         refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider());//设置滚动边界判断
 *         refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter());//自定义滚动边界
 *
 *         refreshLayout.setRefreshHeader(new ClassicsHeader(context));//设置Header
 *         refreshLayout.setRefreshFooter(new ClassicsFooter(context));//设置Footer
 *         refreshLayout.setRefreshContent(new View(context));//设置刷新Content（用于非xml布局代替addView）1.0.4
 *
 *         refreshLayout.autoRefresh();//自动刷新
 *         refreshLayout.autoLoadMore();//自动加载
 *         refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
 *         refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
 *         refreshLayout.finishRefresh();//结束刷新
 *         refreshLayout.finishLoadMore();//结束加载
 *         refreshLayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
 *         refreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
 *         refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
 *         refreshLayout.finishLoadMore(false);//结束加载（加载失败）
 *         refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
 *         refreshLayout.closeHeaderOrFooter();//关闭正在打开状态的 Header 或者 Footer（1.1.0）
 *         refreshLayout.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4（1.1.0删除）
 *         refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5
 *
 *  全局头部局样式
 *
 */

class BaseRefreshHeader//        tvTime.setText(DateUtil.dateToString(Date(), DateUtil.TIME_DEFAULT_FORMAT))
    (context: Context) : LinearLayout(context), RefreshHeader {
    private var tvDes: TextView
    private var ivImg: ImageView
    private lateinit var rotateAnimation: Animation
    private var tvTime: TextView

    init {
        val view =
            View.inflate(context, R.layout.widget_custom_refresh_header, this)
        tvDes = view.findViewById(R.id.tvDes)
        ivImg = view.findViewById(R.id.ivImg)
        tvTime = view.findViewById(R.id.tvTime)
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            tvDes.setTextColor(context.resources.getColor(R.color.color_white))
        } else {
            tvDes.setTextColor(context.resources.getColor(R.color.color_black))
        }
        tvTime.text = "更新于：${2020-12-20}"
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun onStartAnimator(
        layout: RefreshLayout,
        height: Int,
        extendHeight: Int
    ) {
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.PullDownToRefresh -> tvDes!!.text =
                "下拉进行刷新"
            RefreshState.Refreshing -> {
                tvDes!!.text = "刷新中"
                rotateAnimation = RotateAnimation(
                    0f,
                    360f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
                )
                rotateAnimation.setFillAfter(true)
                rotateAnimation.setDuration(500)
                rotateAnimation.setRepeatCount(-1)
                rotateAnimation.setInterpolator(LinearInterpolator())
                ivImg!!.startAnimation(rotateAnimation)
            }
            RefreshState.ReleaseToRefresh -> tvDes!!.text =
                "松开刷新"
        }
    }

    /**
     * 动画结束后调用
     */
    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        rotateAnimation.cancel()
        if (success) {
            tvTime.text = "更新于：2020-12-20"
        }
        return 0
    }

    override fun setPrimaryColors(vararg colors: Int) {}
    override fun onInitialized(
        kernel: RefreshKernel,
        height: Int,
        extendHeight: Int
    ) {
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) { // 绘制根据手势滑动的动画
        if (percent < 1) {
            ivImg!!.scaleX = percent
            ivImg!!.scaleY = percent
            ivImg!!.rotation = percent * 3600
        }
    }

    override fun onReleased(
        refreshLayout: RefreshLayout,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onHorizontalDrag(
        percentX: Float,
        offsetX: Int,
        offsetMax: Int
    ) {
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }
}