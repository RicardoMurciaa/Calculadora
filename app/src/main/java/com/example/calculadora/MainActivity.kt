package com.example.calculadora

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var numero1EditText: EditText
    private lateinit var numero2EditText: EditText
    private lateinit var operationSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    private val calculator = Calculadora()


    private val decimalFormat = DecimalFormat("0.##")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numero1EditText = findViewById(R.id.numero1)
        numero2EditText = findViewById(R.id.numero2)
        operationSpinner = findViewById(R.id.operationSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        setupSpinner()
        calculateButton.setOnClickListener { calculateResult() }
    }

    private fun setupSpinner() {
        val operations = arrayOf("Suma", "Resta", "Multiplicación", "División")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operationSpinner.adapter = adapter
    }

    private fun calculateResult() {
        val numero1Text = numero1EditText.text.toString()
        val numero2Text = numero2EditText.text.toString()

        if (numero1Text.isEmpty() && numero2Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa ambos números.", Toast.LENGTH_SHORT).show()
            return
        }

        if (numero1Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el primer número.", Toast.LENGTH_SHORT).show()
            return
        }

        if (numero2Text.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa el segundo número.", Toast.LENGTH_SHORT).show()
            return
        }

        val numero1 = numero1Text.toDoubleOrNull()
        val numero2 = numero2Text.toDoubleOrNull()

        if (numero1 == null) {
            Toast.makeText(this, "El primer número no es válido.", Toast.LENGTH_SHORT).show()
            return
        }

        if (numero2 == null) {
            Toast.makeText(this, "El segundo número no es válido.", Toast.LENGTH_SHORT).show()
            return
        }

        val operation = operationSpinner.selectedItem.toString()

        try {
            val result = when (operation) {
                "Suma" -> calculator.add(numero1, numero2)
                "Resta" -> calculator.subtract(numero1, numero2)
                "Multiplicación" -> calculator.multiply(numero1, numero2)
                "División" -> calculator.divide(numero1, numero2)
                else -> throw IllegalArgumentException("Operación no válida")
            }
            resultTextView.text = "Resultado: ${decimalFormat.format(result)}"
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al calcular el resultado.", Toast.LENGTH_SHORT).show()
        }
    }
}