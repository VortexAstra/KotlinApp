package poly.company.calc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }

        //Operators
        tvPlus.setOnClickListener { appendOnExpression("+", true) }
        tvMinus.setOnClickListener { appendOnExpression("-", true) }
        tvMul.setOnClickListener { appendOnExpression("*", true) }
        tvDivide.setOnClickListener { appendOnExpression("/", true) }
        tvOpen.setOnClickListener { appendOnExpression("(", true) }
        tvClose.setOnClickListener { appendOnExpression(")", true) }



        tvClear.setOnClickListener {
            tvExpressive.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpressive.text.toString()
            if (string.isNotEmpty()) {
                tvExpressive.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }


        tvEquals.setOnClickListener {

            try {
                val expression = ExpressionBuilder(tvExpressive.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception ", " message :" + e.message)
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Expressive", tvExpressive.text.toString())
        outState.putString("Result", tvResult.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tvExpressive.text = savedInstanceState.getString("Expressive")
        tvResult.text = savedInstanceState.getString("Result")
    }

    private fun appendOnExpression(string: String, canClear: Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpressive.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            tvExpressive.append(string)
        } else {
            tvExpressive.append(tvResult.text)
            tvExpressive.append(string)
            tvResult.text = ""
        }
    }

}

