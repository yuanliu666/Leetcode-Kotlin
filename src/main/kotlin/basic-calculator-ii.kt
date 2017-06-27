/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */
import java.util.*

class Solution_basic_calculator_ii {
    // T:O(n) S:O(n)
    fun calculate(expr: String): Int {
        val nStk = Stack<Int>()
        val opStk = Stack<Char>()
        var temp = ""
        for (i in (expr.length - 1).downTo(0)) {
            if (expr[i].isDigit()) {
                temp += expr[i]
                if (i == 0 || !expr[i - 1].isDigit()) {
                    nStk.push(Integer.parseInt(temp))
                    temp = ""
                }
            } else if (expr[i] in arrayOf('*', '/', ')')) {
                opStk.push(expr[i])
            } else if (expr[i] == '+' || expr[i] == '-') {
                while (opStk.isNotEmpty() && (opStk.peek() == '/' || opStk.peek() == '*'))
                    compute(nStk, opStk)
                opStk.push(expr[i])
            } else if (expr[i] == '(') {
                while (opStk.peek() != ')')
                    compute(nStk, opStk)
                opStk.pop()
            }
        }

        while (opStk.isNotEmpty())
            compute(nStk, opStk)

        return nStk.pop()
    }

    fun compute(nStk: Stack<Int>, opStk: Stack<Char>) {
        val left = nStk.pop()
        val right = nStk.pop()
        val op = opStk.pop()
        when (op) {
            '+' -> nStk.push(left + right)
            '-' -> nStk.push(left - right)
            '*' -> nStk.push(left * right)
            '/' -> nStk.push(left / right)
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution_basic_calculator_ii()
    println(s.calculate("3+2*2"))
    println(s.calculate(" 3/2 "))
    println(s.calculate(" 3+5 / 2 "))
}
