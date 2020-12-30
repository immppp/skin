package cn.imppp.skin.ui.fragment.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.ArticleEntity
import cn.imppp.skin.entity.BaseSystemEntity
import cn.imppp.skin.entity.ProjectTypeEntity
import cn.imppp.skin.entity.SquareEntity
import cn.imppp.skin.repository.net.NetRepository

class HomeViewModel : BaseViewModel() {
    val articleList = MutableLiveData<List<ArticleEntity>>()
    val squareList = MutableLiveData<List<SquareEntity>>()
    val systemList = MutableLiveData<List<BaseSystemEntity>>()
    val projectList = MutableLiveData<List<ProjectTypeEntity>>()

    fun loadArticleList(page: Int) {
        jobLiveData.value = launch(
            block = {
                if (page == 0) {
                    articleList.value = NetRepository.getInstance().articleList(page)
                } else {
                    val tempData = NetRepository.getInstance().articleList(page)
                    val lastData: ArrayList<ArticleEntity>? =
                        articleList.value as ArrayList<ArticleEntity>?
                    for (i in lastData!!.indices) {
                        Log.i("homeViewModel", lastData[i].title)
                    }
                    lastData!!.addAll(tempData)
                    Log.i("homeViewModel", "=========")
                    for (i in lastData!!.indices) {
                        Log.i("homeViewModel", lastData[i].title)
                    }
                    articleList.value = lastData
                }

            },
            error = {
            },
            cancel = {
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

    fun loadSystemList() {
        jobLiveData.value = launch(
            block = {
                systemList.value = NetRepository.getInstance().systemList()
            }
        )
    }

    fun loadProjectTypeList() {
        jobLiveData.value = launch(
            block = {
                projectList.value = NetRepository.getInstance().projectTypeList()
            }
        )
    }
}