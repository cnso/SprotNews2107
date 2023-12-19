package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.ObservableArrayMap
import com.alibaba.android.arouter.facade.annotation.Route
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.logging
import org.jash.common.utils.token
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.ActivityPhoneBinding
import org.jash.sprotnews2107.viewmodel.PhoneViewModel

@Route(path = "/news/phone")
class PhoneActivity : BaseActivity<ActivityPhoneBinding, PhoneViewModel>() {
    val user = ObservableArrayMap<String, String>()
    val phoneVerify = "1(3[0-9]|5[1-9]|7[0-9]|8[0-9]|9[0-9])\\d{8}".toRegex()
    override fun initView() {
        binding.user = user
        binding.login.setOnClickListener {
            viewModel.loginForPhone(user)
        }
        binding.getCode.setOnClickListener {
            if (!phoneVerify.matches(user["phone"] ?: "")) {
                Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.getCode(user["phone"] ?: "")
        }
        binding.phone.editText?.addTextChangedListener {
            if(phoneVerify.matches(it.toString())) {
                binding.phone.isErrorEnabled = false
            } else {
                binding.phone.isErrorEnabled = true
                binding.phone.error = "手机号不合法"
            }
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        viewModel.tokenLiveData.observe(this, this::login)
        viewModel.codeLiveData.observe(this, this::code)
    }
    fun login(str:String) {
        token = str
        logging("token: $str")
        finishAffinity()
    }
    fun code(code:Int) {
        user["code"] = code.toString()
    }
}