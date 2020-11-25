package cn.imppp.skin.ui.activity

import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import cn.imppp.skin.R
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityFragmentBinding
import cn.imppp.skin.state.MainViewModel
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.android.synthetic.main.title_layout.*

class FragmentActivity : BaseActivity<MainViewModel, ActivityFragmentBinding>(), MotionLayout.TransitionListener {

    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_fragment
    }

    override fun loadData() {
        text1.setOnClickListener(this)
        motionLayout.setTransitionListener(this)
        text2.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        super.onClick(p0)
        when(p0?.id) {
            R.id.text2 -> motionLayout.transitionToState(R.id.start)
        }
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        Log.i("fragmentActivity", "state====${motionLayout.startState}  int: $p1" +
                "\n p0====${p0?.currentState}")
//        motionLayout.transitionToState(R.id.two)
    }

}
