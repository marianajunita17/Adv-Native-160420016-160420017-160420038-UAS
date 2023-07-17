package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.view.ButtonLoginClickListener
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel

class LoginFragment : Fragment(), ButtonLoginClickListener {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onLoginClickListener(v: View) {
        TODO("Not yet implemented")
    }

}