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

class Solution_basic_calculator_ii {

    // T:O(n) S:O(n)
    fun calculate(s: String): Int {
        val nStk = java.util.Stack<Long>()
        val opStk = java.util.Stack<Char>()
        var temp = ""
        for (i in (s.length - 1).downTo(0)) {
            if (s[i].isDigit()) {
                temp += s[i]
                if (i == 0 || !s[i - 1].isDigit()) {
                    nStk.push(temp.reversed().toLong())
                    temp = ""
                }
            } else if (s[i] in arrayOf('*', '/', ')')) {
                opStk.push(s[i])
            } else if (s[i] == '+' || s[i] == '-') {
                while (opStk.isNotEmpty() && (opStk.peek() == '/' || opStk.peek() == '*'))
                    compute(nStk, opStk)
                opStk.push(s[i])
            } else if (s[i] == '(') {
                while (opStk.peek() != ')')
                    compute(nStk, opStk)
                opStk.pop()
            }
        }

        while (opStk.isNotEmpty())
            compute(nStk, opStk)

        return nStk.pop().toInt()
    }

    private fun compute(nStk: java.util.Stack<Long>, opStk: java.util.Stack<Char>) {
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
    // LC OJ passed
    val s = Solution_basic_calculator_ii()
    println(s.calculate("3+2*2"))
    println(s.calculate(" 3/2 "))
    println(s.calculate(" 3+5 / 2 "))
}
