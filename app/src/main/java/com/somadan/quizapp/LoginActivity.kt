package com.somadan.quizapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.somadan.quizapp.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Toast.makeText(this, "dukse", Toast.LENGTH_SHORT).show()
        binding.button.setOnClickListener {
            Firebase.auth.createUserWithEmailAndPassword(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful)
                {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Registration Failed : ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}