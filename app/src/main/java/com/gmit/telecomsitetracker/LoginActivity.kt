package com.gmit.telecomsitetracker
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gmit.telecomsitetracker.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var uEmail: EditText
    private lateinit var uPassword: EditText
    private lateinit var btnLogin: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        uEmail = findViewById(R.id.u_email)
        uPassword = findViewById(R.id.u_password)
        btnLogin = findViewById(R.id.btn_login)

        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = uEmail.text.toString()
            val password = uPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful, navigate to next activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Login failed, display error message
                    Toast.makeText(this, "Login failed, Please try again", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
