package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentRegisterBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.view.ButtonRegisterClickListener
import com.mariana.adv160420017uts.view.RadioClickGender
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), ButtonRegisterClickListener, RadioClickGender {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            it.findViewById<BottomNavigationView>(R.id.bottomNav).visibility = View.GONE
            it.findViewById<DrawerLayout>(R.id.drawerLayout).setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.user = User("", "", 1, "", 0, "https://static.vecteezy.com/system/resources/previews/008/442/086/original/illustration-of-human-icon-user-symbol-icon-modern-design-on-blank-background-free-vector.jpg")
        dataBinding.registerListener = this

        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        viewModel.profileLD.observe(viewLifecycleOwner) {
            if (it != null) {
                val action = RegisterFragmentDirections.actionRegisterHomeFragment()
                Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onRegisterClickListener(v: View) {
        dataBinding.user?.let {
            viewModel.register(it) { msg ->
                Toast.makeText(v.context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRadioClickGender(v: View, gender: Int, user: User) {
        user.gender = gender
    }

}