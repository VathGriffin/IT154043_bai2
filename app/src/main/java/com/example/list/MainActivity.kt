package com.example.list
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber: EditText = findViewById(R.id.editTextNumber)
        val radioEven: RadioButton = findViewById(R.id.radioEven)
        val radioOdd: RadioButton = findViewById(R.id.radioOdd)
        val radioSquare: RadioButton = findViewById(R.id.radioSquare)
        val buttonShow: Button = findViewById(R.id.buttonShow)
        val listView: ListView = findViewById(R.id.listView)
        val textViewError: TextView = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            val nStr = editTextNumber.text.toString()
            if (nStr.isEmpty()) {
                textViewError.text = "Vui lòng nhập một số nguyên dương."
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = nStr.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Số không hợp lệ. Vui lòng nhập số nguyên dương."
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            textViewError.visibility = TextView.GONE
            val resultList = mutableListOf<Int>()

            when {
                radioEven.isChecked -> {
                    for (i in 0..n step 2) {
                        resultList.add(i)
                    }
                }
                radioOdd.isChecked -> {
                    for (i in 1..n step 2) {
                        resultList.add(i)
                    }
                }
                radioSquare.isChecked -> {
                    var i = 0
                    while (i * i <= n) {
                        resultList.add(i * i)
                        i++
                    }
                }
                else -> {
                    textViewError.text = "Vui lòng chọn loại số."
                    textViewError.visibility = TextView.VISIBLE
                }
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }
}
