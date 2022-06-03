package com.izmir.learnkotlin.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izmir.learnkotlin.R
import com.izmir.learnkotlin.databinding.ActivityWelcomeActiivityBinding
import com.izmir.learnkotlin.util.Constant

class WelcomeActiivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeActiivityBinding
    private var userName: String = ""
    private var userSurname: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeActiivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            userName = intent.getStringExtra(Constant.USER_NAME_KEY).toString()
            userSurname = intent.getStringExtra(Constant.USER_SURNAME_KEY).toString()
            tvWelcome.text = getString(R.string.welcome_text, userName, userSurname)
        }
    }
}