package cn.imppp.skin.ui.fragment.question

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.MultiTypeAdapter
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.skin.R
import cn.imppp.skin.adapter.mainArticle.MainQuestionBinder
import cn.imppp.skin.adapter.mainArticle.MainSquareBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseFragment
import cn.imppp.skin.databinding.FragmentQuestionLayoutBinding
import kotlinx.android.synthetic.main.fragment_question_layout.*
import kotlinx.android.synthetic.main.square_fragment_layout.*

class QuestionFragment : BaseFragment<QuestionViewModel, FragmentQuestionLayoutBinding>(),
    OnViewClickListener {
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter
    private lateinit var controller: LayoutAnimationController

    override fun viewModelClass(): Class<QuestionViewModel> {
        return QuestionViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_question_layout
    }

    override fun onClick(p0: View?) {
    }

    override fun observe() {
        mViewModel.questionList.observe(this, Observer {
            // 加载数据
            mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {

                for (i in it.indices) {
                    add(MainQuestionBinder(it[i]).apply {
                        setOnClickListener(this@QuestionFragment::onRecycleViewClick)
                    })
                }

            })
        })
    }

    override fun initView() {
        controller = LayoutAnimationController(
            AnimationUtils.loadAnimation(
                App.mWindowsContext,
                R.anim.animate
            )
        )
        rvQuestion.layoutAnimation = controller
        mMultiTypeAdapter =
            createMultiTypeAdapter(rvQuestion, LinearLayoutManager(App.mWindowsContext))
    }

    override fun initData() {
        mViewModel.loadQuestionList(0)
    }

    override fun onRecycleViewClick(view: View) {
    }
}