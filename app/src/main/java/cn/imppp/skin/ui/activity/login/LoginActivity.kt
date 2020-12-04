package cn.imppp.skin.ui.activity.login

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import cn.imppp.skin.R
import cn.imppp.skin.base.App
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.constant.Constant
import cn.imppp.skin.databinding.ActivityLoginLayoutBinding
import cn.imppp.skin.http.RetrofitClient
import cn.imppp.skin.ui.activity.MainActivity
import cn.imppp.skin.utils.MmkvUtils
import kotlinx.android.synthetic.main.activity_login_layout.*

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginLayoutBinding>() {
    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_login_layout
    }

    override fun initData() {

        mViewModel.haveTitleLayout.value = false
        btLoginButton.setOnClickListener(this)
        btRegisterButton.setOnClickListener(this)

        etLoginUser.addTextChangedListener(TextWatcher())
        etLoginPassword.addTextChangedListener(TextWatcher())
        etRegisterAgainPassword.addTextChangedListener(TextWatcher())
        etRegisterPassword.addTextChangedListener(TextWatcher())
        etRegisterUser.addTextChangedListener(TextWatcher())

        etLoginUser.setText(MmkvUtils.decodeString(Constant.spUserName))
        etLoginPassword.setText(MmkvUtils.decodeString(Constant.spPassword))
    }

    override fun observer() {
        mViewModel.resultData.observe(this, Observer {
            MmkvUtils.encode(Constant.spUserName, it.username)
            MmkvUtils.encode(Constant.spPassword, it.password)
            Log.i("cookie", RetrofitClient.cookieJar.cookieStore.getAllCookie().toString())
            MmkvUtils.encode(Constant.spCookie, RetrofitClient.cookieJar.cookieStore.getAllCookie().toString())
            finish()
            startActivity(Intent(App.mWindowsContext, MainActivity::class.java))
        })

        mViewModel.registerData.observe(this, Observer {
            MmkvUtils.encode(Constant.spUserName, it.username)
            MmkvUtils.encode(Constant.spPassword, it.password)
            finish()
            startActivity(Intent(App.mWindowsContext, MainActivity::class.java))
        })
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btLoginButton -> {
                if (TextUtils.isEmpty(etLoginUser.text.toString())) {
                    Toast.makeText(App.mWindowsContext, "请输入用户名", Toast.LENGTH_SHORT).show()
                    return
                }
                if (TextUtils.isEmpty(etLoginPassword.text.toString())) {
                    Toast.makeText(App.mWindowsContext, "请输入密码", Toast.LENGTH_SHORT).show()
                    return
                }
                mViewModel.login(etLoginUser.text.toString(), etLoginPassword.text.toString())
            }
            R.id.btRegisterButton -> {
                if (etRegisterAgainPassword.text.toString() != etRegisterPassword.text.toString()) {
                    Toast.makeText(App.mWindowsContext, "两次输入密码不一致", Toast.LENGTH_SHORT).show()
                    return
                }
                mViewModel.register(etRegisterUser.text.toString(), etRegisterPassword.text.toString())
            }
        }
    }

    inner class TextWatcher() : android.text.TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btLoginButton.isEnabled =
                !(TextUtils.isEmpty(etLoginPassword.text.toString()) || TextUtils.isEmpty(
                    etLoginUser.text.toString()
                ))

            btRegisterButton.isEnabled = !(TextUtils.isEmpty(etRegisterUser.text.toString()) ||
                    TextUtils.isEmpty(etRegisterPassword.text.toString()) ||
                    TextUtils.isEmpty(etRegisterAgainPassword.text.toString()))
        }
    }

}
