package com.example.carloancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener{
            calculateLoan()

        }
    }

    private fun calculateLoan(){
        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error))
            return
        }
        if(editTextDownPayment.text.isEmpty()){  //lower than car price
            editTextDownPayment.setError(getString(R.string.error))
            return
        }
        if(editTextLoanPeriod.text.isEmpty()){ //max 9 year
            editTextLoanPeriod.setError(getString(R.string.error))
            return
        }
        if(editTextInterestRate.text.isEmpty()){
            editTextInterestRate.setError(getString(R.string.error))
            return
        }

        //TODO get all inputs from user and perform calculation
        val carPrice = editTextCarPrice.text.toString().toInt()
        val downPayment = editTextDownPayment.text.toString().toInt()
        val loanPeriod = editTextLoanPeriod.text.toString().toInt()
        val interestRate = editTextInterestRate.text.toString().toFloat()

        val pound = Currency.getInstance(Locale.getDefault())
        val symbol = pound.getSymbol()

        var carLoan = carPrice - downPayment
        var interest = carLoan * interestRate * loanPeriod
        var monthlyRepayment = (carLoan + interest) / loanPeriod / 12.0

        //TODO Display the outputs
        textViewLoan.setText(getString(R.string.loan) + symbol + "${carLoan}")
        textViewInterest.setText(getString(R.string.interest) + symbol + "${interest}")
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment) + symbol + "${monthlyRepayment}")
    }

    fun resetInput(view: View) {
        //TODO: Clear all inputs and outputs
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextInterestRate.setText("")
        editTextLoanPeriod.setText("")
        textViewInterest.setText("")
        textViewLoan.setText("")
        textViewMonthlyRepayment.setText("")

    }
}
