package org.jash.sprotnews2107.ui

import androidx.databinding.ObservableArrayMap
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.logging
import org.jash.common.utils.token
import org.jash.sprotnews2107.databinding.ActivityLoginBinding
import org.jash.sprotnews2107.viewmodel.LoginViewModel
@Route(path = "/news/login")
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    val user = ObservableArrayMap<String, String>().apply {
        put("name", "qqqq")
        put("password", "1111")
    }
    override fun initView() {
        binding.user = user
        binding.login.setOnClickListener {
            viewModel.login(user)
        }
        binding.phone.setOnClickListener {
            ARouter.getInstance()
                .build("/news/phone")
                .navigation()
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        viewModel.tokenLiveData.observe(this, this::login)
    }
    fun login(str:String) {
        token = str
        logging("token: $str")
        finish()
    }
}