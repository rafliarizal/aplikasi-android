package com.example.tryy

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tryy.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var binding : ActivityMainBinding
private lateinit var auth : FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            var username = binding.editUsername.text
            var password = binding.editPassword.text
            signIn("$username", "$password")
        }

//        binding.daftar.setOnClickListener {
//            val register = Intent(this, RegisterActivity::class.java)
//            startActivity(register)
//        }
    }

    fun showLandingPage(){
        val intent = Intent(this, Firstpage::class.java)
        startActivity(intent)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser !== null) showLandingPage()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


//    fun tombol_hitung(view: View) {
//        var bb : Float = binding.editBb.text.toString().toFloat()
//        var tb : Float = (binding.editTb.text.toString().toFloat())/100
//        var index : Float = bb/(tb*tb)
//        binding.tvIndex.text = "index : $index"
//
//        var status = ""
//        if ( index < 18.5 ){
//            status = " Kekurangan berat badan "
//        }else if ( index >= 18.5 && index < 25.0 ) {
//            status = " Normal ( ideal )"
//        }else if ( index >= 25.0 && index < 30.0 ) {
//            status = " Kelebihan berat badan "
//        }else {
//            status = " Kegemukan ( obesitas ) "
//       }
//
//       binding.tvStatus.text = "index : $status"
//
//    }
}