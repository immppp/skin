package cn.imppp.skin.ui.activity.demo

import android.util.Log
import android.view.View
import android.widget.Toast
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.base.AppDatabase
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityCardViewLayoutBinding
import cn.imppp.skin.entity.dao.User
import cn.imppp.skin.ui.activity.login.LoginViewModel
import cn.imppp.skin.utils.DateUtils
import kotlinx.android.synthetic.main.activity_card_view_layout.*

class CardViewActivity:BaseActivity<LoginViewModel, ActivityCardViewLayoutBinding>() {

    private lateinit var userList: List<User>

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
            stringBuffer.append(it.createTimes)
            stringBuffer.append("    ")
            stringBuffer.append(it.updateTimes)
            stringBuffer.append("\n")
        }
        tvCardViewDes.text = stringBuffer.toString()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.tvCardViewClick -> {           // 插入
                val user = User("李四", "张三", 10)
                AppDatabase.instance?.userDao()?.insertData(user)
                queryData()
            }
            R.id.tvCardViewDelete -> {          // 删除
                AppDatabase.instance?.userDao()?.deleteData(userList[0])
                queryData()
            }
            R.id.tvCarViewUpdate -> {           // 插入
                val user = userList[0]
                user.firstName = "张三"
                user.updateTimes = DateUtils.getCurrentDataDefault()
                AppDatabase.instance?.userDao()?.updateData(user)
                queryData()
            }
        }
    }
}