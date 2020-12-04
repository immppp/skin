package cn.imppp.skin.adapter.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter : FragmentStateAdapter {

    private lateinit var mFragments: List<Fragment>

    constructor(activity: FragmentActivity, fragments: List<Fragment>) : super(activity) {
        mFragments = fragments
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }
}