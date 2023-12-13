package org.jash.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.jash.common.utils.logging
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<B:ViewDataBinding, VM:BaseViewModel> : AppCompatActivity() {
    private val types by lazy { (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }
    val binding:B by lazy {
        val clazz = types[0] as Class<B>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, LayoutInflater.from(this)) as B
    }
    val viewModel:VM by lazy {
        ViewModelProvider(this)[types[1] as Class<VM>]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.errorLiveData.observe(this, this::error)
        initView()
        initData()
    }
    abstract fun initView()
    abstract fun initData()

    open fun error(msg:String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        logging("error: $msg")
    }
}