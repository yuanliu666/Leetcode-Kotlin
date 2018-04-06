/**
 * Given two integers n and k, you need to construct a list which contains n different positive integers
 * ranging from 1 to n and obeys the following requirement:
 * Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|]
 * has exactly k distinct integers.
 *
 * If there are multiple answers, print any of them.
 *
 * Example 1:
 * Input: n = 3, k = 1
 * Output: [1, 2, 3]
 * Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3,
 * and the [1, 1] has exactly 1 distinct integer: 1.
 * Example 2:
 * Input: n = 3, k = 2
 * Output: [1, 3, 2]
 * Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3,
 * and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 * Note:
 * The n and k are in the range 1 <= k < n <= 104.
 */

class Solution_beautiful_arrangement_ii {

    // an intuitive way is to use permutation, which is plain brute force of time complexity O(n!)
    // a better approach is try to construct the result since the problem only requires one answer
    // the difference between two elements from 1~n is from 1 to n-1
    // if k = 1 then ascending/descending order list will be the answer
    // if k = n-1 then we extract from top and bottom each time so the difference is ascending/descending
    // e.g. [1, n, 2, n-1, 3, ...], difference is from n-1 to 1
    // what if k is in between?
    // we cam combine both example, namely using 2nd way to get enough different differences,
    // and the put rest in order to avoid too many
    // note since the 2nd way's differences are larger than 1 in this case,
    // the 1st way will still contribute difference = 1
    // so we only need to get k - 1 different differences from 2nd way
    // obviously k numbers are needed to get k - 1 different differences,
    // and then if last number is from small half, we append rest in ascend order, otherwise in descend order

    // T:O(n) S:O(n)
    fun constructArray(n: Int, k: Int): IntArray {
        var k2 = k
        val ret = mutableListOf<Int>()
        var left = 1
        var right = n
        while (left <= right) {
            if (k2 % 2 == 1) {
                ret.add(left)
                left++
            } else {
                ret.add(right)
                right--
            }
            if (k2 > 1) {
                k2--
            }
        }
        return ret.toIntArray()
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
}