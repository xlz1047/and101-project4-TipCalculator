package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val billAmount = binding.billInput.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercent = binding.tipInput.text.toString().toDoubleOrNull() ?: 0.0
        val peopleCount = binding.peopleInput.text.toString().toIntOrNull() ?: 1

        if (billAmount <= 0 || tipPercent < 0) {
            binding.resultText.text = "Please enter valid numbers!"
            return
        }

        val tipAmount = billAmount * (tipPercent / 100)
        val totalBill = billAmount + tipAmount
        val perPerson = totalBill / peopleCount

        val resultText = """
            💵 Tip: $${"%.2f".format(tipAmount)}
            💰 Total: $${"%.2f".format(totalBill)}
            🧍 Each Person: $${"%.2f".format(perPerson)}
        """.trimIndent()

        binding.resultText.text = resultText
    }
}
