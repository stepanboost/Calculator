package com.example.calculator

sealed class CalcOper(val sumbol: String){
    object Add: CalcOper("+")
    object Subtract: CalcOper("-")
    object Multiply: CalcOper("*")
    object Divide: CalcOper("/")

}