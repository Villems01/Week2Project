package com.example.week2project


import android.app.Activity
import android.content.Intent
import android.view.View
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.ImageView

class AddQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editQuestion: EditText = findViewById(R.id.Question_text_input)
        val editAnswer: EditText = findViewById(R.id.Answer_text_input)
        val btnSave: ImageView = findViewById(R.id.SaveButton)

        btnSave.setOnClickListener {
            val question = editQuestion.text.toString()
            val answer = editAnswer.text.toString()

            // Put data into an intent
            val resultIntent = Intent()
            resultIntent.putExtra("QUESTION_KEY", question)
            resultIntent.putExtra("ANSWER_KEY", answer)

            // Send data back
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // dismiss AddQuestion activity
            }

        findViewById<View>(R.id.imgCancelBut).setOnClickListener {
            finish()
        }

    }
}