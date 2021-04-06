package com.sripiranavan.learning.fragmenttest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sripiranavan.learning.fragmenttest.R
import com.sripiranavan.learning.fragmenttest.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {

    private var binding:FragmentHomePageBinding? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentHomePageBinding = FragmentHomePageBinding.inflate(inflater,container,false)
        binding = fragmentHomePageBinding
        fragmentHomePageBinding.applicationButton.setOnClickListener { view ->
                findNavController().navigate(R.id.action_homePageFragment_to_applicationFragment)
        }
        return fragmentHomePageBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}