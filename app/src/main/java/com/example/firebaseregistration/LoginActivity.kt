package com.example.firebaseregistration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.login)
        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener(){
            performLogin()
        }

        val toRegister = findViewById<TextView>(R.id.to_register)
        toRegister.setOnClickListener(){
            val intent= Intent(this, RegisterActivity:: class.java)
            startActivity(intent)
        }
    }
//private fun performLogin (){
//    val email = findViewById<EditText>(R.id.email_edittext)
//    val password = findViewById<EditText>(R.id.email_edittext_reg)
//
//
//    if (email.text.isEmpty() || password.text.isEmpty()){
//        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//        return
//    }
//    val inputEmail = email.text.toString()
//    val inputPassword = password.text.toString()
//    auth.signInWithEmailAndPassword(inputEmail, inputPassword)
//        .addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                // Sign in success, update UI with the signed-in user's information
//                val intent = Intent(this, MainActivity:: class.java)
//                startActivity(intent)
//                Toast.makeText(baseContext, "Yor are auth", Toast.LENGTH_SHORT).show()
//            } else {
//                // If sign in fails, display a message to the user.
//                Toast.makeText(
//                    baseContext,
//                    "Authentication failed.",
//                    Toast.LENGTH_SHORT,
//                ).show()
//            }
//        }.addOnFailureListener{
//            Toast.makeText(this, "Connection is lost", Toast.LENGTH_SHORT).show()
//        }
//
//}

    private fun performLogin() {
        val email = findViewById<EditText>(R.id.email_edittext)
        val password = findViewById<EditText>(R.id.password_edittext)

        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(
                this,
                "Please fill all fields",
                Toast.LENGTH_SHORT,
            ).show()
            return
        }


        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        baseContext,
                        "Succses",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()

                }
            }
            .addOnFailureListener{
                Toast.makeText(
                    this,
                    "Error occurred ${it.localizedMessage}",
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
}
