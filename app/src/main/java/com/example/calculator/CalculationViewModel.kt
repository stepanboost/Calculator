package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class CalculatorViewModel: ViewModel(){
        var state by mutableStateOf(CalculatorState())

    fun onAction(action: CalculationActions) {
            when(action) {
                    is CalculationActions.Number -> enterNumber(action.number)
                    is CalculationActions.Delete -> delete()
                    is CalculationActions.Clear -> state = CalculatorState()
                    is CalculationActions.Operation -> enterOperation(action.operation)
                    is CalculationActions.Decimal -> enterDecimal()
                    is CalculationActions.Calculate -> calculate()
            }
    }

        private fun calculate() {
                val number1 = state.number1.toDoubleOrNull()
                val number2 = state.number2.toDoubleOrNull()
                if(number1 != null && number2 != null) {
                        val result = when(state.operation) {
                                is CalcOper.Add -> number1 + number2
                                is CalcOper.Subtract -> number1 - number2
                                is CalcOper.Multiply -> number1 * number2
                                is CalcOper.Divide -> number1 / number2
                                null -> return
                        }
                        state = state.copy(
                                number1 = result.toString().take(15),
                                number2 = "",
                                operation = null
                        )
                }
        }
        private fun delete() {
                when {
                        state.number2.isNotBlank() -> state = state.copy(
                                number2 = state.number2.dropLast(1)
                        )

                        state.operation != null -> state = state.copy(
                                operation = null
                        )

                        state.number1.isNotBlank() -> state = state.copy(
                                number1 = state.number1.dropLast(1)
                        )
                }
        }

        private fun enterDecimal() {
                if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
                        state = state.copy(
                                number1 = state.number1 + "."
                        )
                        return
                } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
                        state = state.copy(
                                number2 = state.number2 + "."
                        )
                }
        }

        private fun enterOperation(operation: CalcOper) {
                if(state.number1.isNotBlank()) {
                        state = state.copy(operation = operation)
                }

        }

        private fun enterNumber(number: Int) {
                if(state.operation == null) {
                        if(state.number1.length >= MAX_NUM_LENGTH) {
                                return
                        }
                        state = state.copy(
                                number1 = state.number1 + number
                        )
                        return
                }
                if(state.number2.length >= MAX_NUM_LENGTH) {
                        return
                }
                state = state.copy(
                        number2 = state.number2 + number
                )
        }
        companion object {
                private const val MAX_NUM_LENGTH = 8
        }

}