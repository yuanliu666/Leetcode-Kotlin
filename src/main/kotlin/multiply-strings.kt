/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
class Solution_multiply_strings {

    // Using the fact that num1[i] * num2[j] will be placed at indices [i + j, i + j + 1]
    // T:O(m*n) S:O(m+n)
    fun multiply(num1: String, num2: String): String {
        val pos = IntArray(num1.length + num2.length)
        // Reverse order to handle carries
        for (i in num1.indices.reversed()) {
            for (j in num2.indices.reversed()) {
                val mul = (num1[i] - '0') * (num2[j] - '0')
                val p1 = i + j
                val p2 = i + j + 1
                val sum = mul + pos[p2]
                pos[p1] += sum / 10
                pos[p2] = sum % 10
            }
        }
        val sb = StringBuilder()
        // Don't accept leading zeros
        for (p in pos) {
            if (sb.isNotEmpty() || p != 0) sb.append(p)
        }
        return if (sb.isEmpty()) "0" else sb.toString()
    }

    // AC solution with breaking down multiply with add
    // e.g. 123x456 = 100x456+20x456+3x456, and handle 1 digit multiply with add
    // T:O(m*n) S:O(m+n)
    fun multiply2(num1: String, num2: String): String {
        if (num1 == "0" || num2 == "0") return "0"

        var zeros = num1.length - 1
        var ret = "0"
        for (c in num1) {
            val d = c - '0'
            var temp = "0"
            for (i in 0 until d) {
                temp = add(temp, num2)
            }
            if (temp != "0") {
                temp += "0".repeat(zeros)
            }
            zeros--
            ret = add(ret, temp)
        }
        return ret
    }

    // T:O(m+n) S:O(max(m,n))
    private fun add(num1: String, num2: String): String {
        if (num1 == "0") return num2
        if (num2 == "0") return num1

        val n1 = num1.reversed()
        val n2 = num2.reversed()
        var i = 0
        var j = 0
        var c = 0
        val sb = StringBuilder()
        while (i < n1.length || j < n2.length) {
            val d1 = if (i < n1.length) n1[i] - '0' else 0
            val d2 = if (j < n2.length) n2[j] - '0' else 0
            val sum = d1 + d2 + c
            sb.append(sum % 10)
            c = sum / 10
            i++
            j++
        }
        if (c == 1) {
            sb.append(1)
        }
        return sb.toString().reversed()
    }
}