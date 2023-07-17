package com.mariana.adv160420017uts.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentLoginBinding
import com.mariana.adv160420017uts.model.User
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
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.login = this
        dataBinding.register = this
        dataBinding.user = User("", "", "", "", "", "", "", "")
    }

    override fun onLoginClickListener(v: View) {
        viewModel.login(dataBinding?.user!!.username, dataBinding?.user!!.password)

        if(viewModel.result == "Gagal") {
            val builder = AlertDialog.Builder(v.context)
            builder.setTitle("Login Gagal")
        } else {
            Toast.makeText(v.context, "Login Berhasil!", Toast.LENGTH_LONG).show()
            Navigation.findNavController(v).navigate(LoginFragmentDirections.actionLoginHomeFragment())
        }
    }

    override fun onRegisterClickListener(v: View) {
        Navigation.findNavController(v).navigate(RegisterFragmentDirections.actionRegisterHomeFragment())
    }

}