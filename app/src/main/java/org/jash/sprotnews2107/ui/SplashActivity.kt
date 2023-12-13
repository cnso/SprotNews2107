package org.jash.sprotnews2107.ui

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableInt
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jash.common.mvvm.BaseActivity
import org.jash.common.utils.logging
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.ActivitySplashBinding
import org.jash.sprotnews2107.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    val count = ObservableInt(10)
    val isFirst by lazy { getSharedPreferences("first", MODE_PRIVATE).getBoolean("isFirst", true) }
    val dialog by lazy {
        val regex = "《.+?》".toRegex()
        val str = getString(R.string.user_agreement)
        val ss = SpannableString(str)
        val match = regex.findAll(str)
        match.forEach {
            ss.setSpan(ForegroundColorSpan(Color.BLUE), it.range.start, it.range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            val span = object :ClickableSpan() {
                override fun onClick(widget: View) {
                    Toast.makeText(this@SplashActivity, it.value, Toast.LENGTH_SHORT).show()
                }
            }
            ss.setSpan(span, it.range.start, it.range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        }
        MaterialAlertDialogBuilder(this)
            .setTitle("用户协议")
            .setMessage(ss)
            .setPositiveButton("同意") { _, _ ->
                getSharedPreferences("first", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirst", false)
                    .commit()
                Toast.makeText(this, "跳转", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("暂不使用") { _, _ -> finish() }

    }
    override fun initView() {
        binding.count = count
    }

    override fun initData() {
        if( isFirst) {
            dialog.show()
        }
        viewModel.countLiveData.observe(this, this::progress)
        viewModel.start()
    }

    fun progress(i:Int) {
        count.set(i)
        if(i == 0) {
            logging("跳转")
            ARouter.getInstance().build("/news/main")
                .navigation()
            finish()
        }
    }

}