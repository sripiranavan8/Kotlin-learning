package com.sripiranavan.learning.fragmenttest.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sripiranavan.learning.fragmenttest.R
import com.sripiranavan.learning.fragmenttest.databinding.FragmentApplicationBinding
import com.sripiranavan.learning.fragmenttest.viewModel.ApplicationViewModel
import java.util.*


class ApplicationFragment : Fragment() {
    private val applicationViewModel: ApplicationViewModel by activityViewModels<ApplicationViewModel>()
    private var binding:FragmentApplicationBinding?=null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentApplicationPageBinding = DataBindingUtil.inflate<FragmentApplicationBinding>(
            inflater,R.layout.fragment_application,container,false
        )
        binding = fragmentApplicationPageBinding
        return fragmentApplicationPageBinding.root
    }

//    For model bind the fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            applicationModel = applicationViewModel
            applicationFragment = this@ApplicationFragment
        }
    }

    fun goToHome(){
        applicationViewModel.resetForm()
        return findNavController().navigate(R.id.action_applicationFragment_to_homePageFragment);
    }

    fun saveApplicationForm(){
        applicationViewModel.setFirstName(view?.findViewById<EditText>(R.id.firstNameEditText)?.text.toString())
        applicationViewModel.setLastName(view?.findViewById<EditText>(R.id.lastNameEditText)?.text.toString())
        applicationViewModel.setEmail(view?.findViewById<EditText>(R.id.emialAddressEditText)?.text.toString())
        Log.d("Testa",applicationViewModel.email.value.toString())
        applicationViewModel.resetForm()
    }

    fun getDatePicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this@ApplicationFragment.requireActivity(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            val date = dayOfMonth.toString() + "/" + (monthOfYear + 1).toString() + "/" + year.toString()
            applicationViewModel.setDob(date)
        }, year, month, day)
        dpd.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}