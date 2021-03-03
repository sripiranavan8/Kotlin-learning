package com.sripiranavan.learning.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.sripiranavan.learning.authentication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    companion object{
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private val viewModel:LoginViewModel by lazy {
        ViewModelProvider(this,LoginViewModel.Factory(requireActivity().application)).get(LoginViewModel::class.java)
    }

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,R.layout.fragment_login,container,false
        )
        binding.authButton.setOnClickListener { launchSignInFlow() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                        providers
                ).build(), SIGN_IN_RESULT_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE){
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK){
                Log.i(
                        TAG,
                        "Successfully signed in user "+
                                "${FirebaseAuth.getInstance().currentUser?.displayName}"
                )
            }else{
                Log.i(TAG,
                "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }
}