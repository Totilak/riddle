package com.example.riddle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.riddle.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val myText = result.data?.getStringExtra("twoName")
                    if (myText != null) {
                        binding.txtVibar.text=otv[myText.toInt()]
                        if(myText.toInt()==OtvetNum)
                        {
                            coutner++
                            binding.linearLayoutOtvet1.setBackgroundColor(Color.GREEN)
                        }else
                        {
                            binding.linearLayoutOtvet1.setBackgroundColor(Color.RED)
                        }
                        binding.textView4.text="${coutner}/${countercount}"


                    }
                }
            }
    }
    public var coutner = 0
    public var countercount = 0
    public val zagadki = arrayListOf<String>("Что это такое: синий, большой, с усами и полностью набит зайцами?",
        "Не ездок, а со шпорами,Не будильник, а всех будит.",
        "Она красива и мила, А имя ей от слова зола.",
        "Ношу их много лет, А счету им не знаю.",
        "Кто меня сделал, не сказывает. Кто меня не знает, принимает. А кто знает, на двор не пускает.",
        "Если б не было его, Не сказал бы ничего.",
        "Стоит толстуха - Деревянное брюхо, Железный поясок.",
        "Кругом вода, а с питьем беда. ",
        "Тридцать два молотят, один поворачивает.",
        "Зубов много, а ничего не ест.",
        "Я легкий как перышко, но долго меня не удержишь.",
        "Есть всегда у людей Есть всегда у кораблей.",
        "Не ездок, а со шпорами, не сторож, а всех будит",
        "В этот месяц таит все, в этот месяц снег идёт, в этот месяц все теплей, в этот месяц женский день.",
        "Вертится, стрекочет, Весь день хлопочет.")


    public val otv = arrayListOf<String>("Троллейбус","Подушка","Золушка","Волосы","Фальшивая монета","Язык","Бочка","Море","Зубы и язык","Расческа","Вдох","Нос","петух","март","Сорока")
    private  var launcher: ActivityResultLauncher<Intent>? = null
    var exzaga = emptyArray<Int>()
    var OtvetNum = 0

    fun btnGetZagadka(view: View) {
        if(countercount>=10)
        {
            binding.btnZaga.isEnabled=false
            binding.btnrebut.isEnabled=true
            Toast.makeText(getApplicationContext(), "Вы решили правильно ${coutner} загадок из 10!!", Toast.LENGTH_SHORT)
                .show();
            return
        }
        randGenerated()
        binding.txtTextZagadki.text=zagadki[OtvetNum]
        binding.btnZaga.isEnabled=false
        binding.btnOtvet.isEnabled=true
        binding.linearLayoutOtvet1.setBackgroundColor(Color.WHITE)

    }
    fun randGenerated() {
        val rand=Random.nextInt(0, 14)
        val found = exzaga.any { rand == it}
        if(found)
        {
            randGenerated()
        }else
        {
            OtvetNum= rand
            exzaga+=OtvetNum
        }
    }

    fun btnGetOtvet(view: View) {
        binding.btnOtvet.isEnabled=false
        binding.btnZaga.isEnabled=true
        val intent = Intent(this, MainActivity2::class.java)
        launcher?.launch(intent)
        countercount++
        binding.textView5.text=countercount.toString()
    }

    fun btnrebut(view: View) {
        coutner=0
        countercount=0
        binding.btnZaga.isEnabled=true
        binding.btnrebut.isEnabled=false
        binding.txtVibar.text=" "
        binding.textView4.text="0/0"
        binding.textView5.text="0"

    }

}