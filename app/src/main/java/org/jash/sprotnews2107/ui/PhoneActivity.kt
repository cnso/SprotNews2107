package org.jash.sprotnews2107.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun initView() {
        binding.user = user
        binding.login.setOnClickListener {
            viewModel.loginForPhone(user)
        }
        binding.getCode.setOnClickListener {
            viewModel.getCode(user["phone"] ?: "")
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
        finish()
    }
    fun code(code:Int) {
        user["code"] = code.toString()
    }
}