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
import kotlinx.android.synthetic.main.fragment_register.*

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
        dataBinding.user = User("", "", 1, "", 0, "")
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


        if(txtPasswordRegist.length() < 8){
            Toast.makeText(v.context, "Password harus lebih dari 8", Toast.LENGTH_SHORT).show()
        }

        if(txtTelpRegist.length() < 10 && txtTelpRegist.length() > 12){
            Toast.makeText(v.context, "Nomor telepon harus 10 - 12 digit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRadioClickGender(v: View, gender: Int, user: User) {
        user.gender = gender
    }

}