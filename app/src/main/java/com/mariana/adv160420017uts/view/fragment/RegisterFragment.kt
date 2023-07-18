package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentRegisterBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.view.ButtonRegisterClickListener
import com.mariana.adv160420017uts.view.RadioClickGender
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class RegisterFragment : Fragment(), ButtonRegisterClickListener, RadioClickGender {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        dataBinding.register = User("", "", 0, "", "", "", "", "")
        dataBinding.registerListener = this
    }

    override fun onRegisterClickListener(v: View) {
        viewModel.register(dataBinding.register!!)
        Toast.makeText(v.context, "Register Berhasil!", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onRadioClickGender(v: View, gender: Int, obj: User) {
        obj.gender = gender
    }

}