package com.example.calculadora;

class Calculadora {
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b

    fun divide(a: Double, b: Double): Double {

        if (b == 0.0) throw IllegalArgumentException("No se puede dividir por cero")
        return a / b

    }
}

