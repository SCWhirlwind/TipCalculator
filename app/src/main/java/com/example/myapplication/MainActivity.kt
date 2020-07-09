package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.Contacts
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

const val MAX_PEOPLE = 10

private var tipAmount = 0.00
private var taxAmount = 0.00
private var tipPercent = 0
private var peopleAmount = 1

private var taxSwitch = false
private var peopleDivide = false

class MainActivity : AppCompatActivity(), TextWatcher, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TipInput.addTextChangedListener(this)
        TaxInput.addTextChangedListener(this)
        TipSeekBar.setOnSeekBarChangeListener(this)
        PeopleSeekBar.setOnSeekBarChangeListener(this)
        TaxSwitch.setOnCheckedChangeListener(this)
        PeopleSwitch.setOnCheckedChangeListener(this)

        PeopleSeekBar.max = MAX_PEOPLE
    }

    override fun afterTextChanged(input: Editable?) {
    }

    private fun calculateExpense()
    {
        if (TipInput.editableText.isNotEmpty() && TaxInput.editableText.isNotEmpty())
        {
            tipAmount = TipInput.text.toString().toDouble()
            taxAmount = TaxInput.text.toString().toDouble()
            TotalCostValue.text = "$" + TipCalculator().calculatePerPersonTotal(tipAmount, peopleAmount, tipPercent, taxAmount, taxSwitch, peopleDivide).toString()
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (TipInput.text.isNotEmpty() && TaxInput.text.isNotEmpty())
        {
            calculateExpense()
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, p1: Int, p2: Boolean) {
        when (seekBar)
        {
            TipSeekBar -> {
                tipPercent = TipSeekBar.progress
                TipPercentValue.text = "$tipPercent%"
            }
            PeopleSeekBar -> {
                peopleAmount = if (PeopleSeekBar.progress < 1 ) {
                    1
                }
                else {
                    PeopleSeekBar.progress
                }
                PeopleAmountValue.text = "$peopleAmount"
                }
        }
        calculateExpense()
    }


    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    override fun onCheckedChanged(button: CompoundButton?, p1: Boolean) {
        when (button)
        {
            TaxSwitch -> {
                taxSwitch = TaxSwitch.isChecked
                changeTaxText(taxSwitch)
            }
            PeopleSwitch -> {
                peopleDivide = PeopleSwitch.isChecked
                changeSplitText(peopleDivide)
            }
        }
        calculateExpense()
    }

    private fun changeTaxText(tax: Boolean)
    {
        if (tax)
        {
            TaxSwitch.text = "Tip After Tax"
        }
        else
        {
            TaxSwitch.text = "Tip Before Tax"
        }
    }

    private fun changeSplitText(split: Boolean)
    {
        if (split)
        {
            PeopleSwitch.text = "Split Enabled"
        }
        else
        {
            PeopleSwitch.text = "Split Disabled"
        }
    }
}