package com.mariana.adv160420017uts.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mariana.adv160420017uts.R
import com.mariana.adv160420017uts.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_balance.*

class BalanceFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            txtSaldoBalance.setText(it.saldo)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }
}