/**
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie. Each child i has a greed factor gi,
 * which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i,
 * and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 *
 * Note:
 * You may assume the greed factor is always positive.
 * You cannot assign more than one cookie to one child.
 *
 * Example 1:
 * Input: [1,2,3], [1,1]
 *
 * Output: 1
 *
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1,
 * you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 * Example 2:
 * Input: [1,2], [1,2,3]
 *
 * Output: 2
 *
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 */

class Solution_assign_cookies {
    // An intuitive solution is to use greedy: we satisfy children with smaller gi first
    // So we need to sort children
    // Also we should try to use correct cookie size. Sorting cookie is a straightforward approach
    // And then use double pointer

    // T:O(nlogn) S:O(1)
    fun getMaxSatisfiedCnt(children: Array<Int>, cookies: Array<Int>): Int {
        var ret = 0
        children.sort()
        cookies.sort()
        var i = 0
        var j = 0
        while (i < children.size && j < cookies.size) {
            when {
                cookies[j] >= children[i] -> {
                    ret++
                    i++
                    j++
                }
                else -> j++
            }
        }
        return ret
    }
}

fun main(args: Array<String>) {
    println(Solution_assign_cookies().getMaxSatisfiedCnt(arrayOf(1, 2, 3), arrayOf(1, 1)))
    println(Solution_assign_cookies().getMaxSatisfiedCnt(arrayOf(1, 2), arrayOf(1, 2, 3)))
}