package com.example.week2project

import android.content.Intent
import android.view.View
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val questionText: TextView = findViewById(R.id.flashcard_question)
        val answerText: TextView = findViewById(R.id.flashcard_answer)
        val imgAdd: ImageView = findViewById(R.id.imgAddQuestion)

        // Register for activity result
        val addQuestionLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val newQuestion = data?.getStringExtra("QUESTION_KEY")
                val newAnswer = data?.getStringExtra("ANSWER_KEY")

                if (!newQuestion.isNullOrEmpty()) {
                    questionText.text = newQuestion
                }
                if (!newAnswer.isNullOrEmpty()) {
                    answerText.text = newAnswer
                    answerText.visibility = android.view.View.INVISIBLE // hide answer until tapped
                }
            }
        }

        // Launch AddQuestion activity when imgAdd is clicked
        imgAdd.setOnClickListener {
            val intent = Intent(this, AddQuestion::class.java)
            addQuestionLauncher.launch(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets

            }
            // Toggle flashcard on click
            questionText.setOnClickListener {
                questionText.visibility = android.view.View.INVISIBLE
                answerText.visibility = android.view.View.VISIBLE
            }
            answerText.setOnClickListener {
                answerText.visibility = android.view.View.INVISIBLE
                questionText.visibility = android.view.View.VISIBLE
            }

        }
        fun showAnswer(view: View) {}
    }


