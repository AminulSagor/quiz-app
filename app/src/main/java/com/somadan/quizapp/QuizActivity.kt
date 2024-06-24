package com.somadan.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.somadan.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    private lateinit var list: ArrayList<QuestionModel>
    private  var count:Int=0
    private var score=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        list= ArrayList<QuestionModel>()

        Firebase.firestore.collection("quiz").get().addOnSuccessListener {
            doct->
            list.clear()
            for(i in doct.documents)
            {
                var questionModel= i.toObject(QuestionModel::class.java)
                list.add(questionModel!!)
            }
            binding.question.setText(list.get(0).question)
            binding.option1Btn.setText(list.get(0).option1)
            binding.option2Btn.setText(list.get(0).option2)
            binding.option3Btn.setText(list.get(0).option3)
            binding.option4Btn.setText(list.get(0).option4)

        }



        binding.option1Btn.setOnClickListener { nextData(binding.option1Btn.text.toString()) }
        binding.option2Btn.setOnClickListener { nextData(binding.option2Btn.text.toString()) }
        binding.option3Btn.setOnClickListener { nextData(binding.option3Btn.text.toString()) }
        binding.option4Btn.setOnClickListener { nextData(binding.option4Btn.text.toString()) }

    }
    private  fun nextData (i : String)
    {

        if (count<list.size){
            if (list.get(count).ans.equals(i)){
                score++
            }
        }
        count++
        if (count>=list.size){
            val intent= Intent(this,ScoreActivity::class.java)
            intent.putExtra("SCORE",score)
            startActivity(intent)
            finish()
        }
        else{
            binding.question.setText(list.get(count).question)
            binding.option1Btn.setText(list.get(count).option1)
            binding.option2Btn.setText(list.get(count).option2)
            binding.option3Btn.setText(list.get(count).option3)
            binding.option4Btn.setText(list.get(count).option4)
        }

    }
}