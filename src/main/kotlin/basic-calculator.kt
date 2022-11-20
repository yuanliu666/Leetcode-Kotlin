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

class Solution_basic_calculator {

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
            } else if (s[i] in arrayOf('+', '-', ')')) {
                opStk.push(s[i])
            } else if (s[i] == '(') {
                while (opStk.peek() != ')')
                    compute(nStk, opStk)
                opStk.pop()
            }
        }

        while (opStk.isNotEmpty()) {
            compute(nStk, opStk)
        }

        return nStk.pop().toInt()
    }

    private fun compute(nStk: java.util.Stack<Long>, opStk: java.util.Stack<Char>) {
        val left = nStk.pop()
        val right = nStk.pop()
        val op = opStk.pop()
        if (op == '+')
            nStk.push(left + right)
        else
            nStk.push(left - right)
    }

    // Update version with allowing '-' as unary operation: e.g. "-2+1", "-(-2)"
    // The key is to save sign as well, to handle case like "1-(-2)"
    // T:O(n) S:O(n)
    fun calculate2(s: String): Int {
        val stk = Stack<Int>()
        var ret = 0
        var num = 0
        var sign = 1
        for (i in s.indices) {
            val c = s[i]
            when {
                c.isDigit() -> num = num * 10 + (c - '0')
                c == '+' -> {
                    ret += sign * num
                    num = 0
                    sign = 1
                }

                c == '-' -> {
                    ret += sign * num
                    num = 0
                    sign = -1
                }

                c == '(' -> {
                    stk.push(ret)
                    stk.push(sign)
                    sign = 1
                    ret = 0
                }

                c == ')' -> {
                    ret += sign * num
                    num = 0
                    ret *= stk.pop()
                    ret += stk.pop()
                }
            }
        }
        if (num != 0) ret += sign * num
        return ret
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_basic_calculator()
    println(s.calculate("1 + 1"))
    println(s.calculate(" 2-1 + 2 "))
    println(s.calculate("(1+(4+5+2)-3)+(6+8)"))
}
