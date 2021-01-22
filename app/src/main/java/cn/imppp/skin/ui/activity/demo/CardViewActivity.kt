package cn.imppp.skin.ui.activity.demo

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.MultiTypeAdapter
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.skin.R
import cn.imppp.skin.adapter.demo.DemoBinder
import cn.imppp.skin.adapter.project.ProjectBinder
import cn.imppp.skin.base.App
import cn.imppp.skin.base.AppDatabase
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityCardViewLayoutBinding
import cn.imppp.skin.entity.dao.User
import cn.imppp.skin.ui.activity.login.LoginViewModel
import cn.imppp.skin.utils.DateUtils
import cn.imppp.skin.widght.ItemTouchHelperUtils
import kotlinx.android.synthetic.main.activity_card_view_layout.*
import kotlinx.android.synthetic.main.fragment_project_layout.*

class CardViewActivity:BaseActivity<LoginViewModel, ActivityCardViewLayoutBinding>() {

    private lateinit var userList: List<User>
    private lateinit var mMultiTypeAdapter: MultiTypeAdapter

    override fun layoutRes(): Int {
        return R.layout.activity_card_view_layout
    }

    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initData() {
        tvCardViewClick.setOnClickListener(this)
        mViewModel.haveTitleLayout.value = false
        tvCarViewUpdate.setOnClickListener(this)
        tvCardViewDelete.setOnClickListener(this)
        queryData()

        val mList = mutableListOf<String>()
        for (i in 0..20) {
            mList.add("数据 $i")
        }

        mMultiTypeAdapter = createMultiTypeAdapter(rvCardView, GridLayoutManager(App.mWindowsContext, 4))
        mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            for (i in mList.indices) {
                add(DemoBinder(mList[i])).apply {
                }
            }
        })
        ItemTouchHelperUtils.getInstance(mMultiTypeAdapter, mList, App.mWindowsContext)
        val helper: ItemTouchHelper = ItemTouchHelperUtils.helper
        helper.attachToRecyclerView(rvCardView)

    }

    private fun queryData() {
        userList = AppDatabase.instance?.userDao()?.getAllData()!!
        Log.i("list", "${userList.size}   $userList")
        val stringBuffer = StringBuffer()
        userList.forEach{
            stringBuffer.append(it.seqNum)
            stringBuffer.append(it.fatherName)
            stringBuffer.append("   ")
            stringBuffer.append(it.firstName)
            stringBuffer.append(DateUtils.longToDate(it.createTimes, DateUtils.DATE_FORMATE_NORMAL).toString())
            stringBuffer.append("    ")
            stringBuffer.append(it.updateTimes)
            stringBuffer.append("\n")
        }
        tvCardViewDes.text = stringBuffer.toString()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.tvCardViewClick -> {           // 插入
                val list = mutableListOf<String>()
                list.add("123")
                list.add("456")
                val user = User("李四", "张三", 10, list = list)
                AppDatabase.instance?.userDao()?.insertData(user)
//                val request = RequestDataEntity("123456", "http//www.baiDu.com")
//                AppDatabase.instance?.requestDataDao()?.insertData(request)
                queryData()
            }
            R.id.tvCardViewDelete -> {          // 删除
                AppDatabase.instance?.userDao()?.deleteData(userList[0])
                queryData()
            }
            R.id.tvCarViewUpdate -> {           // 插入
                val user = userList[0]
                user.firstName = "张三"
//                user.updateTimes = DateUtils.getCurrentDataDefault()
                AppDatabase.instance?.userDao()?.updateData(user)
                queryData()
            }
        }
    }
}