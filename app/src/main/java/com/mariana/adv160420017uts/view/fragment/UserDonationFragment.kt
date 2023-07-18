package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.view.ButtonUserDonateClickListener

class UserDonationFragment : BottomSheetDialogFragment(), ButtonUserDonateClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_donation, container, false)
    }

    override fun onUserDonateClickListener(v: View) {
        TODO("Not yet implemented")
    }
}