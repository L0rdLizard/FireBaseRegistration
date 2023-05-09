package com.example.firebaseregistration

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebaseregistration.ui.theme.FireBaseRegistrationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.registration)
        val toLogin = findViewById<TextView>(R.id.to_login)
        toLogin.setOnClickListener(){
            val intent= Intent(this, LoginActivity:: class.java)
            startActivity(intent)
        }
        val registerButton = findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener(){
            registration()
        }
    }

    private fun registration() {
        val email = findViewById<EditText>(R.id.email_edittext_reg)
        val password = findViewById<EditText>(R.id.password_edittext_reg)

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

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        baseContext,
                        "Succses",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Toast.makeText(
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
