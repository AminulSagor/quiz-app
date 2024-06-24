package com.somadan.quizapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.somadan.quizapp.databinding.ActivityQuizBinding
import com.somadan.quizapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityScoreBinding.inflate(layoutInflater)
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
        binding.score.setText("Congatulation \nYour score is ${intent.getIntExtra("SCORE",0)}")
    }
}