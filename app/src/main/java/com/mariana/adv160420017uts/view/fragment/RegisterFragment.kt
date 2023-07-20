package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentRegisterBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.view.ButtonRegisterClickListener
import com.mariana.adv160420017uts.view.RadioClickGender
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class RegisterFragment : Fragment(), ButtonRegisterClickListener, RadioClickGender {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.user = User("", "", 1, "", "", "", 0, "")
        dataBinding.registerListener = this
    }

    override fun onRegisterClickListener(v: View) {
        dataBinding.user?.let {
            viewModel.register(it) {success ->
                if (success) {
                    viewModel.sessionLogin(it.username, it.saldo)
                    val action = RegisterFragmentDirections.actionRegisterHomeFragment()
                    Navigation.findNavController(v).navigate(action)
                } else {
                    Toast.makeText(v.context, "Registrasi gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRadioClickGender(v: View, gender: Int, user: User) {
        user.gender = gender
    }

}