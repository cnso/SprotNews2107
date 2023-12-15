package org.jash.sprotnews2107.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import org.jash.common.mvvm.BaseFragment
import org.jash.common.utils.token
import org.jash.sprotnews2107.R
import org.jash.sprotnews2107.databinding.FragmentMineBinding
import org.jash.sprotnews2107.viewmodel.MineViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Route(path = "/news/mine")
class MineFragment : BaseFragment<FragmentMineBinding, MineViewModel>() {
    override fun initView() {
        binding.logout.setOnClickListener { viewModel.logout() }
    }

    override fun initData() {
        viewModel.logoutLiveData.observe(viewLifecycleOwner, this::logout)
    }
    fun logout(str:String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
        token = null
        (requireActivity() as NewsActivity).binding.navMain.selectedItemId = R.id.nav_home
    }
}