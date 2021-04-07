package com.sripiranavan.learning.fragmenttest.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApplicationViewModel: ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName:LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName:LiveData<String> = _lastName

    private val _email = MutableLiveData<String>()
    val email:LiveData<String> = _email

    private val _dob = MutableLiveData<String>()
    val dob:LiveData<String> = _dob

    init {
        resetForm()
        Log.d("Testa","From Application View Model")
    }

    fun setFirstName(name:String){
        _firstName.value = name
    }

    fun setLastName(name:String){
        _lastName.value = name
    }

    fun setEmail(email:String){
        _email.value = email
    }

    fun setDob(dob:String){
        _dob.value = dob
    }

    fun resetForm(){
        _email.value = ""
        _firstName.value = ""
        _lastName.value = ""
        _dob.value = ""
    }
}