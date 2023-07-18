package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentProfileBinding
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.view.ButtonEditProfile
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ButtonEditProfile {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner) {
            dataBinding.profile = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val username = ""
        val telp = ""
        val saldo = ""
        Toast.makeText(view.context, username, Toast.LENGTH_SHORT).show()
        viewModel.profile(username, telp, saldo)
        dataBinding.profile = this
        observeViewModel()
    }

    override fun onEditProfile(v: View) {
        val action = ProfileFragmentDirections.actionProfileEdit()
        Navigation.findNavController(v).navigate(action)
    }
}