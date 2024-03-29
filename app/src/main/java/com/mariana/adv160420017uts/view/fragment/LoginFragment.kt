package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentLoginBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.addUserData
import com.mariana.adv160420017uts.view.ButtonLoginClickListener
import com.mariana.adv160420017uts.view.ButtonRegisterClickListener
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class LoginFragment : Fragment(), ButtonLoginClickListener, ButtonRegisterClickListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.onBackPressedDispatcher.addCallback(requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.moveTaskToBack(true)
                }
            })

            it.findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
            it.findViewById<DrawerLayout>(R.id.drawerLayout).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        dataBinding.login = this
        dataBinding.register = this
        dataBinding.user = User("", "", 1, "",  0, "")

//        viewModel.initDB()

        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        viewModel.profileLD.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = LoginFragmentDirections.actionLoginHomeFragment()
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(view.context, "Login tidak berhasil", Toast.LENGTH_SHORT).show()
            }
        }

        if (viewModel.isLogin()) {
            val action = LoginFragmentDirections.actionLoginHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onLoginClickListener(v: View) {
        dataBinding.user?.let {
            viewModel.login(it.username, it.password) { msg ->
                Toast.makeText(v.context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRegisterClickListener(v: View) {
        val action = LoginFragmentDirections.actionRegisterFragment()
        Navigation.findNavController(v).navigate(action)
    }

}