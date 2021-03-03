package com.sripiranavan.learning.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.sripiranavan.learning.authentication.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    companion object{
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this,LoginViewModel.Factory(requireActivity().application)).get(LoginViewModel::class.java)
    }

    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        viewModel.getFactToDisplay(requireContext())
        binding.welcomeText.text = viewModel.getFactToDisplay(requireContext())
        binding.authButton.text = getString(R.string.login_btn)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
        binding.authButton.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun observeAuthenticationState() {
        var factToDisplay = viewModel.getFactToDisplay(requireContext())
    }

    private fun getFactWithPersonalization(fact: String): String{
        return String.format(
            resources.getString(
                R.string.welcome_message_authed,
                FirebaseAuth.getInstance().currentUser?.displayName,
                Character.toLowerCase(fact[0])+ fact.substring(1)
            )
        )
    }

    private fun launchSignInFlow(){

    }
}