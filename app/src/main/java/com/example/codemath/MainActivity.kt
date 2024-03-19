package com.example.codemath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the Spinner
        val spinner: Spinner = findViewById(R.id.spinnerNumberOfPeople)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.number_of_people_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Initializing the EditTexts and Button
        val editTextBillAmount: EditText = findViewById(R.id.editTextBillAmount)
        val editTextCustomTip: EditText = findViewById(R.id.editTextCustomTip)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)

        // Initializing the TextViews for displaying results
        val textViewTotalTip: TextView = findViewById(R.id.textViewTotalTip)
        val textViewAmountPerPerson: TextView = findViewById(R.id.textViewAmountPerPerson)

        // Set a click listener on the Calculate button
        buttonCalculate.setOnClickListener {
            val billAmount = editTextBillAmount.text.toString().toDoubleOrNull()
            val customTip = editTextCustomTip.text.toString().toDoubleOrNull()
            val numberOfPeople = spinner.selectedItem.toString().toIntOrNull()

            // Perform calculation if the input is valid
            if (billAmount != null && numberOfPeople != null && numberOfPeople > 0) {
                val tipPercentage = customTip ?: 15.0 // Use a default tip percentage if no custom tip is entered
                val totalTip = billAmount * (tipPercentage / 100)
                val totalBillWithTip = billAmount + totalTip
                val amountPerPerson = totalBillWithTip / numberOfPeople

                // Update the TextViews to display the calculated results
                textViewTotalTip.text = getString(R.string.total_tip, totalTip)
                textViewAmountPerPerson.text = getString(R.string.amount_per_person, amountPerPerson)
            } else {
                // Show an error message if the input is invalid
                Toast.makeText(this, "Please enter valid amounts", Toast.LENGTH_LONG).show()
            }
        }

        // Spinner item selection handling
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // You can handle spinner item selection here if needed
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: handle the case where no spinner item is selected
            }
        }
    }
}
