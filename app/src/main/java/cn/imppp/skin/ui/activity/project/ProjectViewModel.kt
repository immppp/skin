package cn.imppp.skin.ui.activity.project

import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.ProjectEntity
import cn.imppp.skin.repository.net.NetRepository

class ProjectViewModel : BaseViewModel() {

    val projectList = MutableLiveData<List<ProjectEntity>>()
    val page = MutableLiveData<Int>(1)
    val cid = MutableLiveData(10086)

    fun loadProjectList(page: Int, cid: Int) {
        launch(
            block = {
                projectList.value = NetRepository.getInstance().projectList(page, cid)
            }
        )
    }

}