package com.example.tryy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tryy.databinding.ActivityFirstpageBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityFirstpageBinding

class Firstpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstpage)

        binding.logout.setOnClickListener {logout()}
        binding.bmi.setOnClickListener {halBMI()}
    }

    fun halBMI() {
        val intent = Intent(this, BNIActivity::class.java)
        startActivity(intent)
    }

    fun logout(){
        Firebase.auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)


    }
}