package com.sripiranavan.learning.authentication

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    companion object{
        const val TAG = "SettingsFragment"
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this,LoginViewModel.Factory(requireActivity().application)).get(LoginViewModel::class.java)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}