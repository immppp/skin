package cn.imppp.skin.ui.activity.single

import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.adapter.single.SingleBinder
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.SingleEntity
import kotlinx.coroutines.delay

class SingleRecyclerViewModel : BaseViewModel() {

    val data = MutableLiveData<MutableList<SingleEntity>>()

    fun loadData() {
        launch(
            block = {
//                delay(3000)
                val list = mutableListOf<SingleEntity>()
                list.add(SingleEntity("张三", "18", "0", "计算机爱好者"))
                list.add(SingleEntity("李四", "18", "10", "围棋爱好者"))
                list.add(SingleEntity("王五", "18", "5", "计算机爱好者"))
                list.add(SingleEntity("赵六", "18", "0", "算数爱好者"))
                data.value = list
            }
        )
    }

}