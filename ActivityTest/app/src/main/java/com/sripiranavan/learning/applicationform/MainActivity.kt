package com.sripiranavan.learning.applicationform

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.doneButton).setOnClickListener {
            addNickName(it)
        }
        findViewById<TextView>(R.id.nickname_text).setOnClickListener {
            updateNickName(it)
        }
    }

    private fun addNickName(view:View){
        val nickNameEditText = findViewById<EditText>(R.id.nickName)
        val nickNameTextView = findViewById<TextView>(R.id.nickname_text)
        nickNameTextView.text = nickNameEditText.text
        nickNameEditText.visibility = View.GONE
        view.visibility = View.GONE
        nickNameTextView.visibility = View.VISIBLE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    private fun updateNickName(view:View){
        val nickNameEditText = findViewById<EditText>(R.id.nickName)
        val doneButton = findViewById<Button>(R.id.doneButton)
        nickNameEditText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        nickNameEditText.requestFocus()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(nickNameEditText,0)
    }

}