package com.example.pr13

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pr13.databinding.ActivityAnswerListBinding


class AnswerList : AppCompatActivity() {
    private lateinit var binding: ActivityAnswerListBinding
    private var getAnswer = "Не выбран"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val radioGroup: RadioGroup = findViewById(binding.Group.id)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply { getAnswer = text.toString() }
        }
        CreateRadioButtons()
    }
    fun Check(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("answerRadio", getAnswer)
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun CreateRadioButtons(){
        var mass = arrayOf<String>()
        for (i in intent.getStringArrayExtra("answers")!!.indices.shuffled().take(10)) {
            val rb = RadioButton(this)
            rb.text = intent.getStringArrayExtra("answers")!![i].substringAfterLast("+")
            rb.id = View.generateViewId()
            binding.Group.addView(rb)
            mass += intent.getStringArrayExtra("answers")!![i].substringAfterLast("+")
        }
        if (!mass.contains(intent.getStringExtra("correctAnswer"))){
            (binding.Group.getChildAt((0..9).random()) as RadioButton).text = intent.getStringExtra("correctAnswer")
        }
    }
}