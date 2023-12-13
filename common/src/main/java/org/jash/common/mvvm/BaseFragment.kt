package org.jash.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.jash.common.utils.logging
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<B: ViewDataBinding, VM:BaseViewModel>:Fragment() {
    private val types by lazy { (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments }
    lateinit var binding:B
    val viewModel:VM by lazy {
        ViewModelProvider(this)[types[1] as Class<VM>]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val clazz = types[0] as Class<B>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, inflater) as B
        viewModel.errorLiveData.observe(viewLifecycleOwner, this::error)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()

    open fun error(msg:String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        logging("error: $msg")
    }
}