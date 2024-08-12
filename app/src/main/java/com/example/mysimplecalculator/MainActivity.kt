package com.example.mysimplecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var screen: EditText

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screen = findViewById(R.id.screen)


        val clearBtn = findViewById<Button>(R.id.clear_btn)
        val bracketBtn = findViewById<Button>(R.id.bracket_btn)
        val percentageBtn = findViewById<Button>(R.id.percentage_btn)
        val dividesBtn = findViewById<Button>(R.id.divides_btn)
        val seven = findViewById<Button>(R.id.seven)
        val four = findViewById<Button>(R.id.four)
        val one = findViewById<Button>(R.id.one)
        val delete = findViewById<Button>(R.id.delete)
        val eight = findViewById<Button>(R.id.eight)
        val nine = findViewById<Button>(R.id.nine)
        val times = findViewById<Button>(R.id.times)
        val five = findViewById<Button>(R.id.five)
        val six = findViewById<Button>(R.id.six)
        val minus = findViewById<Button>(R.id.minus)
        val two = findViewById<Button>(R.id.two)
        val three = findViewById<Button>(R.id.three)
        val plus = findViewById<Button>(R.id.plus)
        val zero = findViewById<Button>(R.id.zero)
        val dot = findViewById<Button>(R.id.dot)
        val equal = findViewById<Button>(R.id.equal)


        setButtonListener(seven)
        setButtonListener(four)
        setButtonListener(one)
        setButtonListener(eight)
        setButtonListener(nine)
        setButtonListener(five)
        setButtonListener(six)
        setButtonListener(two)
        setButtonListener(three)
        setButtonListener(zero)
        setButtonListener(dot)

        clearBtn.setOnClickListener {
            screen.text.clear()
        }

        bracketBtn.setOnClickListener {
            screen.append("()")
        }

        percentageBtn.setOnClickListener {
            screen.append("%")
        }

        dividesBtn.setOnClickListener {
            screen.append("/")
        }

        times.setOnClickListener {
            screen.append("*")
        }

        minus.setOnClickListener {
            screen.append("-")
        }

        plus.setOnClickListener {
            screen.append("+")
        }

        delete.setOnClickListener {
            val text = screen.text.toString()
            if (text.isNotEmpty()) {
                screen.text.delete(text.length - 1, text.length)
            }
        }

        equal.setOnClickListener {
            try {
                val expression = screen.text.toString()
                val result = evaluateExpression(expression)
                screen.setText(result.toString())
            } catch (e: Exception) {
                screen.setText("Error")
            }
        }
    }

    private fun setButtonListener(button: Button) {
        button.setOnClickListener {
            screen.append(button.text)
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val cleanExpression = expression.replace("%", "/100")
        return try {
            val result = ExpressionBuilder(cleanExpression).build().evaluate()
            result
        } catch (e: Exception) {
            Double.NaN
        }
    }
}
