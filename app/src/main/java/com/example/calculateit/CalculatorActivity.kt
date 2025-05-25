package com.example.calculateit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.lang.Exception

class CalculatorActivity : AppCompatActivity() {
    private lateinit var firstNumber: TextInputEditText
    private lateinit var secondNumber: TextInputEditText
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        firstNumber = findViewById(R.id.firstNumber)
        secondNumber = findViewById(R.id.secondNumber)
        resultText = findViewById(R.id.resultText)

        findViewById<Button>(R.id.addButton).setOnClickListener { calculate(Operation.ADD) }
        findViewById<Button>(R.id.subtractButton).setOnClickListener { calculate(Operation.SUBTRACT) }
        findViewById<Button>(R.id.multiplyButton).setOnClickListener { calculate(Operation.MULTIPLY) }
        findViewById<Button>(R.id.divideButton).setOnClickListener { calculate(Operation.DIVIDE) }
    }

    private enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private fun calculate(operation: Operation) {
        try {
            val num1 = firstNumber.text.toString().toDouble()
            val num2 = secondNumber.text.toString().toDouble()
            
            val result = when (operation) {
                Operation.ADD -> num1 + num2
                Operation.SUBTRACT -> num1 - num2
                Operation.MULTIPLY -> num1 * num2
                Operation.DIVIDE -> {
                    if (num2 == 0.0) throw Exception("Cannot divide by zero")
                    num1 / num2
                }
            }

            // Format the result to remove unnecessary decimal places
            val formattedResult = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                String.format("%.2f", result)
            }

            resultText.text = "Result: $formattedResult"
        } catch (e: Exception) {
            resultText.text = "Error: ${e.message ?: "Invalid input"}"
        }
    }
} 