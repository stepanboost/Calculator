package com.example.calculator

sealed class CalculationActions {
    data class Number(val number: Int): CalculationActions()
    object Clear: CalculationActions()
    object Delete: CalculationActions()
    data class Operation(val operation: CalcOper): CalculationActions()
    object Calculate: CalculationActions()
    object Decimal: CalculationActions()
}
