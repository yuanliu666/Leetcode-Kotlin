/**
 * Define S = [s,n] as the string S which consists of n connected strings s.
 * For example, ["abc", 3] ="abcabcabc".
 *
 * On the other hand, we define that string s1 can be obtained from string s2
 * if we can remove some characters from s2 such that it becomes s1.
 * For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.
 *
 * You are given two non-empty strings s1 and s2 (each at most 100 characters long)
 * and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2].
 * Find the maximum integer M such that [S2,M] can be obtained from S1.
 *
 * Example:
 *
 * Input:
 * s1="acb", n1=4
 * s2="ab", n2=2
 *
 * Return:
 * 2
 */

class Solution_count_the_repetitions {

    // reference: https://leetcode.com/problems/count-the-repetitions/discuss/95398/C%2B%2B-solution-inspired-by-%4070664914-with-organized-explanation
    // T:O(s1*min(s2,n1))
    // S:O(s2)
    fun getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int {
        val repeatCount = IntArray(s2.length + 1, { 0 })
        // making nextIndex a map with (j, k) pair so that we can access the previous j directly
        val lookup = mutableMapOf<Int, Int>()
        var j = 0
        var count = 0
        for (k in 1 until n1 + 1) {
            for (i in 0 until s1.length) {
                if (s1[i] == s2[j]) {
                    j = (j + 1) % s2.length
                    count += if (j == 0) 1 else 0
                }
            }
            // see if you have met this nextIndex before
            if (j in lookup) {
                // if found, you can calculate the 3 parts
                val i: Int = lookup[j]!!
                // prefixCount is the start-th repeatCount
                val prefixCount: Int = repeatCount[i]
                // (repeatCount[k] - repeatCount[start]) is the repeatCount of one occurrance of the pattern
                // There are (n1 - start) / (k - start) occurrances of the pattern
                // So (repeatCount[k] - repeatCount[start]) * (n1 - start) / (k - start) is the repeatCount of the repetitive part
                val patternCount: Int = (count - repeatCount[i]) * ((n1 - i) / (k - i))
                // The suffix contains the incomplete repetitive remnant (if any)
                // Its length is (n1 - start) % (k - start)
                // So the suffix repeatCount should be repeatCount[start + (n1 - start) % (k - start)] - repeatCount[start]
                val suffixCount: Int = repeatCount[i + (n1 - i) % (k - i)] - repeatCount[i]
                return (prefixCount + patternCount + suffixCount) / n2
            }
            // record the k-th repeatCount and nextIndex
            lookup[j] = k
            repeatCount[k] = count
        }
        return repeatCount[n1] / n2
    }
}

fun main(args: Array<String>) {

}