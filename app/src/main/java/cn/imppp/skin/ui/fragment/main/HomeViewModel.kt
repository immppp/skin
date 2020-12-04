package cn.imppp.skin.ui.fragment.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.ArticleEntity
import cn.imppp.skin.entity.SquareEntity
import cn.imppp.skin.repository.NetRepository

class HomeViewModel : BaseViewModel() {
    val articleList = MutableLiveData<List<ArticleEntity>>()
    val squareList = MutableLiveData<List<SquareEntity>>()

    fun loadArticleList(page: Int) {
        jobLiveData.value = launch(
            block = {
                articleList.value = NetRepository.getInstance().articleList(page)
            },
            error = {
            },
            cancel = {
                Log.i("ThreadTag", "cancel mainViewModel${Thread.currentThread().id}")
                Log.i("requestData", "cancel 返回的数据结果为：${it.message}")
            }
        )
    }

    fun loadSquareList(page: Int) {
        jobLiveData.value = launch(
            block = {
                squareList.value = NetRepository.getInstance().squareList(page)
            }
        )
    }
}