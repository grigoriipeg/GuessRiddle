package com.example.pr13

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.pr13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    private val zagadki = mutableListOf(
            "Два конца, два кольца, посредине гвоздик.+Ножницы",
            "Конь бежит,земля дрожит.+Гром",
            "Зимой и летом одним цветом.+Ёлка",
            "Два брюшка, четыре ушка.+Подушка",
            "Висит сито, не руками свито.+Паутина",
            "Сговорились две ноги делать дуги и круги.+Циркуль",
            "Два братца в воду глядятся, век не сойдутся.+Берега",
            "Стоял на крепкой ножке, теперь лежит в лукошке!+Гриб",
            "Идёт, идёт, до берега дойдёт – исчезнет.+Волна",
            "От одного очага весь свет греется.+Солнце",
            "Без рук, без топорёнка построена избенка.+Гнездо",
            "Никого не обижает, а её все толкают.+Дверь",
            "На верхушке стебелька солнышко и облака.+Ромашка",
            "Зонтик этот ты не тронь – обжигает, как огонь.+Медуза",
            "Мчится печка впереди, тащит избы позади.+Поезд",
        ).shuffled()
    private var wins = 0
    private var loses = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result: ActivityResult ->
            if (result.resultCode == RESULT_OK){
                binding.txtAnswer.text = result.data?.getStringExtra("answerRadio")
                Check()
                Buttons(false)
                if (binding.txtCount.text == "10/10") End()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun GenerateZagadka(view: View){
        binding.txtZagadka.text = zagadki[binding.txtCount.text.split('/')[0].toInt()].substringBeforeLast("+")
        binding.txtCount.text = (binding.txtCount.text.split('/')[0].toInt() + 1).toString() + "/10"
        binding.txtAnswer.setBackgroundColor(Color.TRANSPARENT)
        binding.txtAnswer.text = ""
        Buttons(true)
    }
    fun OpenAnswerList(view: View){
        val intent = Intent(this, AnswerList::class.java)
        intent.putExtra("answers", zagadki.toTypedArray())
        intent.putExtra("correctAnswer", zagadki[binding.txtCount.text.split('/')[0].toInt() - 1].substringAfterLast("+"))
        launcher?.launch(intent)
    }
    fun Statistic(view: View){
        val intent = Intent(this, Statistic::class.java)
        intent.putExtra("wins", wins.toString())
        intent.putExtra("loses", loses.toString())
        startActivity(intent)
    }
    fun Restart(view: View) {
        finish()
        startActivity(intent)
    }
    fun Exit(view: View) = finishAndRemoveTask()
    fun Check(){
        if (zagadki[binding.txtCount.text.split('/')[0].toInt()-1].contains(binding.txtAnswer.text)){
            wins++
            binding.txtAnswer.setBackgroundColor(Color.GREEN)
        }
        else {
            loses++
            binding.txtAnswer.setBackgroundColor(Color.RED)
        }
    }
    private fun Buttons(value: Boolean){
        binding.btnZagadka.isEnabled = !value
        binding.btnAnswer.isEnabled = value
    }
    fun End(){
        binding.btnAnswer.isEnabled = false
        binding.btnZagadka.isEnabled = false
        binding.btnStat.isEnabled = true
        binding.txtAnswer.isVisible = false
        binding.txtZagadka.isVisible = false
        binding.txtYourAnswer.isVisible = false
        binding.txtCount.isVisible = false
        binding.LinearLayoutButtons.isVisible = true
    }
}
