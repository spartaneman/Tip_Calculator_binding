package com.example.tip_calculator_binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tip_calculator_binding.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var tipCalc: TipCalculator

    var money = NumberFormat.getCurrencyInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculator.setOnClickListener {
            calculate()
        }
    }
    private fun calculate() {
        tipCalc = TipCalculator()
        val billString: String = binding.amountBill.text.toString()
        val tipString: String = binding.amountTipPercent.text.toString()
        try {
// convert billString and tipString to floats
            val billAmount = billString.toFloat()
            val tipPercent = tipString.toFloat()
// update the Model
            tipCalc.bill=billAmount
            tipCalc.tip=tipPercent
// tipCalc.bill=billAmount
// ask Model to calculate tip and total amounts
            val tip = tipCalc.tipAmount()
            val total = tipCalc.totalAmount()
// update the View with formatted tip and total amounts
            binding.amountTip.setText(money.format(tip.toDouble()))
            binding.amountTotal.setText(money.format(total.toDouble()))
        } catch (nfe: NumberFormatException) {
// pop up an alert view here
        }
    }
}