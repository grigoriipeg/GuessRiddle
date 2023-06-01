package com.example.pr13

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pr13.databinding.ActivityStatisticBinding

class Statistic : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textCount.text = (intent.getStringExtra("wins")!!.toInt() + intent.getStringExtra("loses")!!.toInt()).toString()
        binding.textCorrect.text = intent.getStringExtra("wins")
        binding.textUncorrect.text = intent.getStringExtra("loses")
    }
    fun Return(view: View) = finish()
}