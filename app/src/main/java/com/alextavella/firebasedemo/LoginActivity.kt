package com.alextavella.firebasedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.alextavella.firebasedemo.extensions.getText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.util.Log


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btRegister.setOnClickListener {
            val email: String = tilEmail.getText() // txEmail.text.toString()
            val password: String = tilPassword.getText() // txPassword.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = mAuth.getCurrentUser()
                            Log.i("TAG", user?.email)
                            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show()
//                            updateUI(user)
                        } else {
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
//                            updateUI(null)
                        }
                    })
        }

        btLogin.setOnClickListener {
            val email: String = tilEmail.getText() // txEmail.text.toString()
            val password: String = tilPassword.getText() // txPassword.text.toString()

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = mAuth.currentUser
                            Log.i("TAG", user?.email)
                            Toast.makeText(this, "Logon", Toast.LENGTH_SHORT).show()
//                            updateUI(user)
                        } else {
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
//                            updateUI(null)
                        }
                    }
        }

        btLogout.setOnClickListener {
            mAuth.signOut()
        }

        btSendMail.setOnClickListener {
            val user : FirebaseUser? = mAuth.currentUser
            user?.sendEmailVerification()
                    ?.addOnCompleteListener(this, OnCompleteListener {
                        task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Email was sent", Toast.LENGTH_SHORT).show()
                        }
                    })
        }
    }
}
