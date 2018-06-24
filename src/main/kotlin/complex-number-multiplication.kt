/**
 * Given two strings representing two complex numbers.
 *
 * You need to return a string representing their multiplication.
 * Note i^2 = -1 according to the definition.
 *
 * Example 1:
 * Input: "1+1i", "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 *
 * Example 2:
 * Input: "1+-1i", "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 * Note:
 *
 * The input strings will not have extra blank.
 * The input strings will be given in the form of a+bi,
 * where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
 */

class Solution_complex_number_multiplication {

    fun complexNumberMultiply(a: String, b: String): String {
        val (aInt, aComplex) = breakDown(a)
        val (bInt, bComplex) = breakDown(b)
        val newInt = aInt * bInt - (aComplex * bComplex)
        val newComplex = aInt * bComplex + aComplex * bInt
        return "$newInt+${newComplex}i"
    }

    // break down string to complex and integer part
    private fun breakDown(s: String): Pair<Int, Int> {
        val paras: List<String> = s.split("+")
        return Pair(paras[0].toInt(), paras[1].replace("i", "").toInt())
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [ComplexNumberMultiplicationTest] for unit test
}