package com.mariana.adv160420017uts.view

import android.view.View
import com.mariana.adv160420017uts.model.Donation
import com.mariana.adv160420017uts.model.User

interface ButtonMyDetailDonateClickListener {
    fun onDetailMyDonateClickListener(v: View)
}

interface ButtonMyDonateClickListener {
    fun onButtonMyDonate(v: View)
}

interface ButtonLoginClickListener {
    fun onLoginClickListener(v: View)
}

interface ButtonRegisterClickListener {
    fun onRegisterClickListener(v: View)
}

interface RadioClickGender {
    fun onRadioClickGender(v: View, gender: Int, obj: User)
}

interface ButtonDonateClickListener {
    fun onDonateClickListener(v: View)
}

interface ButtonDetailDonateClickListener {
    fun onDetailDonateClickListener(v: View, donation: Donation)
}

interface ButtonEditProfile {
    fun onEditProfile(v: View)
}

interface ButtonSaveEditProfile {
    fun onSaveEditProfile(v: View, obj: User)
}

interface ButtonUserDonateClickListener {
    fun onUserDonateClickListener(v: View)
}

interface SwipeRefreshInterface {
    fun onRefresh()
}

interface ButtonClickInterface {
    fun logout(v: View)
}

interface ProgressBarInterface {
    fun progressBarValue(terkumpul: String, target: String): Int
}

interface ButtonDonateClick {
    fun donate(view: View, donation: Donation)
}