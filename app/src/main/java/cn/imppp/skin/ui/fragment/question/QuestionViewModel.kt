package cn.imppp.skin.ui.fragment.question

import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.QuestionEntity
import cn.imppp.skin.repository.NetRepository

class QuestionViewModel : BaseViewModel() {
    val questionList = MutableLiveData<List<QuestionEntity>>()

    fun loadQuestionList(page: Int) {
        jobLiveData.value = launch(
            block = {
                questionList.value = NetRepository.getInstance().questionList(page)
            },
            error = {

            },
            cancel = {

            }
        )
    }

}