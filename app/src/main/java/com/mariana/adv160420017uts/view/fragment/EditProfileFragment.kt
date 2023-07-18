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
import com.mariana.adv160420017uts.databinding.FragmentEditProfileBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.util.loadImage
import com.mariana.adv160420017uts.view.ButtonEditProfile
import com.mariana.adv160420017uts.view.ButtonSaveEditProfile
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class EditProfileFragment : Fragment(), ButtonSaveEditProfile {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentEditProfileBinding

    fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner) {
            dataBinding.editProfile = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater, R.layout.fragment_edit_profile, container, false)
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val username = ""
        viewModel.profile(username)
        dataBinding.saveEditProfileListener = this

        observeViewModel()
    }

    override fun onSaveEditProfile(v: View, obj: User) {
        val username = ""
        viewModel.editProfile(obj.dob, obj.profession, obj.numberTelp, obj.username)
        Toast.makeText(v.context, "Profile Berhasil Di Update!", Toast.LENGTH_SHORT).show()
        val action = EditProfileFragmentDirections.actionEditProfile(username)
        Navigation.findNavController(v).navigate(action)
    }
}