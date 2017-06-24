/**
 * A sequence of number is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequence:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair
 * of integers (P, Q) such that 0 <= P < Q < N.
 *
 * A slice (P, Q) of array A is called arithmetic if the sequence:
 * A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 *
 * The function should return the number of arithmetic slices in the array A.
 *
 * Example:
 *
 * A = [1, 2, 3, 4]
 *
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */

class Solution_arithmetic_slice {
    // An observation: a slice with length L will have L - 2 slices related to last element,
    // and those are new slices compared to previous slice without last element
    // T:O(n) S:O(1)
    fun getCnt(arr: Array<Int>): Int {
        var ret = 0
        var i = 0
        while (i + 2 < arr.size) {
            val start = i
            while (i + 2 < arr.size && arr[i + 2] + arr[i] == 2 * arr[i + 1]) {
                ret += i - start + 1
                i++
            }
            i++
        }
        return ret
    }
}

fun main(args: Array<String>) {
    println(Solution_arithmetic_slice().getCnt(arrayOf(1, 2, 3, 4)))
}
