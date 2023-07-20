package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.databinding.FragmentEditProfileBinding
import com.mariana.adv160420017uts.model.User
import com.mariana.adv160420017uts.view.ButtonSaveEditProfile
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class EditProfileFragment : Fragment(), ButtonSaveEditProfile {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding: FragmentEditProfileBinding

    private fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner) {
            Log.d("Edit Profile", "${it.username}")
            dataBinding.user = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        observeViewModel()
        dataBinding.saveEditProfileListener = this
    }

    override fun onSaveEditProfile(v: View, obj: User) {
        viewModel.editProfile(obj) {
            if (it) {
                Toast.makeText(v.context, "Profile Berhasil Di Update!", Toast.LENGTH_SHORT).show()

                val action = EditProfileFragmentDirections.actionEditProfile()
                Navigation.findNavController(v).navigate(action)
            } else {
                Toast.makeText(v.context, "Update profile gagal!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}