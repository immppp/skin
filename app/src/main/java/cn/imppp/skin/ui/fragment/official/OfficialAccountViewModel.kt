package cn.imppp.skin.ui.fragment.official

import androidx.lifecycle.MutableLiveData
import cn.imppp.skin.base.BaseViewModel
import cn.imppp.skin.entity.OfficialAccountEntity
import cn.imppp.skin.repository.net.NetRepository

/**
 * 公众号fragment
 */

class OfficialAccountViewModel : BaseViewModel(){

    val officialList = MutableLiveData<List<OfficialAccountEntity>>()

    fun loadOfficialList() {
        jobLiveData.value = launch(
            block = {
                officialList.value = NetRepository.getInstance().officialAccountList()
            },
            cancel = {

            },
            error = {

            }
        )
    }

}