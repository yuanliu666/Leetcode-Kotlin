/**
 * Given an expression such as expression = "e + 8 - a + 5" and
 * an evaluation map such as {"e": 1} (given in terms of evalvars = ["e"] and evalints = [1]),
 * return a list of tokens representing the simplified expression, such as ["-1*a","14"]
 * - An expression alternates chunks and symbols, with a space separating each chunk and symbol.
 * - A chunk is either an expression in parentheses, a variable, or a non-negative integer.
 * - A variable is a string of lowercase letters (not including digits.)
 *   Note that variables can be multiple letters, and note that variables never
 *   have a leading coefficient or unary operator like "2x" or "-x".
 *
 * Expressions are evaluated in the usual order:
 * brackets first, then multiplication, then addition and subtraction.
 * For example, expression = "1 + 2 * 3" has an answer of ["7"].
 *
 * The format of the output is as follows:
 * - For each term of free variables with non-zero coefficient,
 *   we write the free variables within a term in sorted order lexicographically.
 *   For example, we would never write a term like "b*a*c", only "a*b*c".
 * - Terms have degree equal to the number of free variables being multiplied,
 *   counting multiplicity. (For example, "a*a*b*c" has degree 4.)
 *   We write the largest degree terms of our answer first,
 *   breaking ties by lexicographic order ignoring the leading coefficient of the term.
 * - The leading coefficient of the term is placed directly to the left with an asterisk separating it
 *   from the variables (if they exist.) A leading coefficient of 1 is still printed.
 * - An example of a well formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"]
 * - Terms (including constant terms) with coefficient 0 are not included.
 *    For example, an expression of "0" has an output of [].
 *
 * Examples:
 *
 * Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
 * Output: ["-1*a","14"]
 *
 * Input: expression = "e - 8 + temperature - pressure",
 * evalvars = ["e", "temperature"], evalints = [1, 12]
 * Output: ["-1*pressure","5"]
 *
 * Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
 * Output: ["1*e*e","-64"]
 *
 * Input: expression = "7 - 7", evalvars = [], evalints = []
 * Output: []
 *
 * Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
 * Output: ["5*a*b*c"]
 *
 * Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))",
 * evalvars = [], evalints = []
 * Output:
 * ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c",
 *  "1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b",
 *  "2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
 *
 * Note:
 * - expression will have length in range [1, 1000].
 * - evalvars, evalints will have equal lengths in range [0, 1000].
 */


class Solution_basic_calculator_iv {

    // note: referenced Leetcode's official java solution
    // Time Complexity: Let N is the length of expression and M is the length of evalvars and evalints.
    // With an expression like (a + b) * (c + d) * (e + f) * ... the complexity is O(2^N + M)
    // Space Complexity: O(N + M)

    // the key question is: how do we want to represent a polynomial?
    // it could have an coefficient and some multipliers, which can be duplicate
    // also we need to figure out + - * operations for it, and format function
    inner class Poly {
        // use a variable to track list of multipliers and int coefficient
        private val count: MutableMap<List<String>, Int> = java.util.HashMap()

        fun update(key: List<String>, value: Int) {
            count[key] = count.getOrDefault(key, 0) + value
        }

        fun add(that: Poly): Poly {
            val ans = Poly()
            for (k in that.count.keys) {
                ans.update(k, that.count[k] ?: 0)
            }
            for (k in count.keys) {
                ans.update(k, count[k] ?: 0)
            }
            return ans
        }

        fun sub(that: Poly): Poly {
            val ans = Poly()
            for (k in count.keys) {
                ans.update(k, count[k] ?: 0)
            }
            for (k in that.count.keys) {
                ans.update(k, -(that.count[k] ?: 0))
            }
            return ans
        }

        fun mul(that: Poly): Poly {
            val ans = Poly()
            for (k1 in count.keys) {
                for (k2 in that.count.keys) {
                    val kNew = mutableListOf<String>()
                    kNew.addAll(k1)
                    kNew.addAll(k2)
                    ans.update(kNew.sorted(), (count[k1] ?: 0) * (that.count[k2] ?: 0))
                }
            }
            return ans
        }

        fun evaluate(evalMap: Map<String, Int>): Poly {
            val ans = Poly()
            for (k in count.keys) {
                var c = count[k] ?: 0
                val free = mutableListOf<String>()
                for (token in k) {
                    if (evalMap.containsKey(token)) {
                        c *= evalMap[token] ?: 0
                    } else {
                        free.add(token)
                    }
                }
                ans.update(free, c)
            }
            return ans
        }

        private fun compareList(A: List<String>, B: List<String>): Int {
            for ((i, x) in A.withIndex()) {
                val y = B[i]
                if (x.compareTo(y) != 0) {
                    return x.compareTo(y)
                }
            }
            return 0
        }

        fun toList(): List<String> {
            val ans = mutableListOf<String>()
            val keys: List<List<String>> = count.keys.toList()
            java.util.Collections.sort(keys, { a, b ->
                if (a.size != b.size) {
                    b.size - a.size
                } else {
                    compareList(a, b)
                }
            })
            for (k in keys) {
                val v = count[k] ?: 0
                if (v == 0) {
                    continue
                }
                val word = StringBuilder()
                word.append(v)
                for (token in k) {
                    word.append("*")
                    word.append(token)
                }
                ans.add(word.toString())
            }
            return ans
        }
    }

    private fun make(expr: String): Poly {
        val ans = Poly()
        val list = mutableListOf<String>()
        if (expr.first().isDigit()) {
            ans.update(list, expr.toInt())
        } else {
            list.add(expr)
            ans.update(list, 1)
        }
        return ans
    }

    private fun combine(left: Poly, right: Poly, op: Char) =
            when (op) {
                '+' -> left.add(right)
                '-' -> left.sub(right)
                '*' -> left.mul(right)
                else -> throw IllegalArgumentException("illegal op $op")
            }

    private fun parse(expr: String): Poly {
        val bucket = mutableListOf<Poly>()
        val ops = mutableListOf<Char>()
        var i = 0
        while (i < expr.length) {
            if (expr[i] == '(') {
                var bal = 0
                var j = i
                lp@ while (j < expr.length) {
                    if (expr[j] == '(') {
                        bal++
                    }
                    if (expr[j] == ')') {
                        bal--
                    }
                    if (bal == 0) {
                        break@lp
                    }
                    j++
                }
                // use recursive method to handle brackets
                bucket.add(parse(expr.substring(i + 1, j)))
                i = j
            } else if (expr[i].isLetterOrDigit()) {
                var j = i
                run {
                    while (j < expr.length) {
                        if (expr[j] == ' ') {
                            bucket.add(make(expr.substring(i, j)))
                            return@run
                        }
                        j++
                    }
                    bucket.add(make(expr.substring(i)))
                }
                i = j
            } else if (expr[i] != ' ') {
                ops.add(expr[i])
            }
            i++
        }

        for (j in (0 until ops.size).reversed()) {
            if (ops[j] == '*') {
                bucket[j] = combine(bucket[j], bucket.removeAt(j + 1), ops.removeAt(j))
            }
        }

        var ans = Poly()
        if (bucket.isEmpty()) {
            return ans
        }
        ans = bucket[0]
        for (j in 0 until ops.size)
            ans = combine(ans, bucket[j + 1], ops[j])
        return ans
    }

    fun basicCalculatorIV(expression: String, evalvars: Array<String>, evalints: IntArray): List<String> {
        val evalMap: MutableMap<String, Int> = HashMap()
        for (i in 0 until evalvars.size) {
            evalMap[evalvars[i]] = evalints[i]
        }
        return parse(expression).evaluate(evalMap).toList()
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_basic_calculator_iv()
    println(s.basicCalculatorIV("e + 8 - a + 5", arrayOf("e"), intArrayOf(1)))
    println(s.basicCalculatorIV("e - 8 + temperature - pressure", arrayOf("e", "temperature"), intArrayOf(1, 12)))
    println(s.basicCalculatorIV("(e + 8) * (e - 8)", arrayOf(), intArrayOf()))
    println(s.basicCalculatorIV("7 - 7", arrayOf(), intArrayOf()))
    println(s.basicCalculatorIV("a * b * c + b * a * c * 4", arrayOf(), intArrayOf()))
    println(s.basicCalculatorIV("((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c - a))", arrayOf(), intArrayOf()))
}