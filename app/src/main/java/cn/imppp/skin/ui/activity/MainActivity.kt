package cn.imppp.skin.ui.activity

import android.graphics.Color
import android.view.View
import androidx.fragment.app.Fragment
import cn.imppp.skin.R
import cn.imppp.skin.adapter.main.MainPagerAdapter
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityMainBinding
import cn.imppp.skin.state.MainViewModel
import cn.imppp.skin.ui.fragment.main.HomeFragment
import cn.imppp.skin.ui.fragment.mine.MineFragment
import cn.imppp.skin.ui.fragment.official.OfficialAccountFragment
import cn.imppp.skin.ui.fragment.question.QuestionFragment
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

        QMUIStatusBarHelper.translucent(this, Color.TRANSPARENT)

        mViewModel.haveTitleLayout.value = false

        val mFragments = mutableListOf<Fragment>()
        mFragments.add(HomeFragment())
        mFragments.add(QuestionFragment())
        mFragments.add(OfficialAccountFragment())
        mFragments.add(MineFragment())
        val mainAdapter = MainPagerAdapter(this, mFragments)
        viewPager.adapter = mainAdapter
        // 禁止viewpager滑动
        viewPager.isUserInputEnabled = false
        viewPager.offscreenPageLimit = 1
    }

    override fun loadData() {
        ivMainPicMine.isEnabled = false
        ivMainPicOne.isEnabled = true
        ivMainPicAccount.isEnabled = false
        ivMainPicQuestion.isEnabled = false
        tvMainOne.setTextColor(App.mWindowsContext.resources.getColor(R.color.main_choose_color))

        llMainTagMine.setOnClickListener(this)
        llMainTagOne.setOnClickListener(this)
        llMainTagAccount.setOnClickListener(this)
        llMainTagQuestion.setOnClickListener(this)
    }

    override fun observer() {
    }

    override fun onClick(p0: View?) {
        super.onClick(p0)
        when (p0?.id) {
            R.id.llMainTagOne -> {
                ivMainPicMine.isEnabled = false
                ivMainPicOne.isEnabled = true
                ivMainPicAccount.isEnabled = false
                ivMainPicQuestion.isEnabled = false
                viewPager.setCurrentItem(0, false)
                tvMainOne.setTextColor(App.mWindowsContext.resources.getColor(R.color.main_choose_color))
                tvMainMine.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainAccount.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainQuestion.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
            }
            R.id.llMainTagMine -> {
                ivMainPicMine.isEnabled = true
                ivMainPicOne.isEnabled = false
                ivMainPicAccount.isEnabled = false
                ivMainPicQuestion.isEnabled = false
                viewPager.setCurrentItem(3, false)
                tvMainOne.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainMine.setTextColor(App.mWindowsContext.resources.getColor(R.color.main_choose_color))
                tvMainAccount.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainQuestion.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
            }
            R.id.llMainTagAccount-> {
                ivMainPicMine.isEnabled = false
                ivMainPicOne.isEnabled = false
                ivMainPicAccount.isEnabled = true
                ivMainPicQuestion.isEnabled = false
                viewPager.setCurrentItem(2, false)
                tvMainOne.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainMine.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainAccount.setTextColor(App.mWindowsContext.resources.getColor(R.color.main_choose_color))
                tvMainQuestion.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
            }
            R.id.llMainTagQuestion-> {
                ivMainPicMine.isEnabled = false
                ivMainPicOne.isEnabled = false
                ivMainPicAccount.isEnabled = false
                ivMainPicQuestion.isEnabled = true
                viewPager.setCurrentItem(1, false)
                tvMainOne.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainMine.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainAccount.setTextColor(App.mWindowsContext.resources.getColor(R.color.color_white))
                tvMainQuestion.setTextColor(App.mWindowsContext.resources.getColor(R.color.main_choose_color))
            }
        }
    }

}
