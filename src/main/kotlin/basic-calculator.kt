/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 */
import java.util.*

class Solution_basic_calculator {
    // T:O(n) S:O(n)
    fun calculate(expr: String): Int {
        val nStk = Stack<Int>()
        val opStk = Stack<Char>()
        var temp = ""
        for (i in (expr.length - 1).downTo(0)) {
            if (expr[i].isDigit()) {
                temp += expr[i]
                if (i == 0 || !expr[i - 1].isDigit()) {
                    nStk.push(Integer.parseInt(temp.reversed()))
                    temp = ""
                }
            } else if (expr[i] in arrayOf('+', '-', ')')) {
                opStk.push(expr[i])
            } else if (expr[i] == '(') {
                while (opStk.peek() != ')')
                    compute(nStk, opStk)
                opStk.pop()
            }
        }

        while (opStk.isNotEmpty()) {
            compute(nStk, opStk)
        }

        return nStk.pop()
    }

    fun compute(nStk: Stack<Int>, opStk: Stack<Char>) {
        val left = nStk.pop()
        val right = nStk.pop()
        val op = opStk.pop()
        if (op == '+')
            nStk.push(left + right)
        else
            nStk.push(left - right)

    }
}

fun main(args: Array<String>) {
    val s = Solution_basic_calculator()
    println(s.calculate("1 + 1"))
    println(s.calculate(" 2-1 + 2 "))
    println(s.calculate("(1+(4+5+2)-3)+(6+8)"))
}
