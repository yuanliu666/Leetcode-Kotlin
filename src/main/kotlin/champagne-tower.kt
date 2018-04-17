/**
 * We stack glasses in a pyramid, where the first row has 1 glass,
 * the second row has 2 glasses, and so on until the 100th row.
 * Each glass holds one cup (250ml) of champagne.
 *
 * Then, some champagne is poured in the first glass at the top.
 * When the top most glass is full, any excess liquid poured
 * will fall equally to the glass immediately to the left and right of it.
 * When those glasses become full, any excess champagne will fall equally to the left and right of those glasses,
 * and so on.  (A glass at the bottom row has it's excess champagne fall on the floor.)
 *
 * For example, after one cup of champagne is poured, the top most glass is full.
 * After two cups of champagne are poured, the two glasses on the second row are half full.
 * After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.
 * After four cups of champagne are poured, the third row has the middle glass half full,
 * and the two outside glasses are a quarter full, as pictured below
 * (https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/09/tower.png).
 *
 * Now after pouring some non-negative integer cups of champagne,
 * return how full the j-th glass in the i-th row is (both i and j are 0 indexed.)
 *
 * Example 1:
 * Input: poured = 1, query_glass = 1, query_row = 1
 * Output: 0.0
 * Explanation: We poured 1 cup of champagne to the top glass of the tower (which is indexed as (0, 0)).
 * There will be no excess liquid so all the glasses under the top glass will remain empty.
 *
 * Example 2:
 * Input: poured = 2, query_glass = 1, query_row = 1
 * Output: 0.5
 * Explanation: We poured 2 cups of champagne to the top glass of the tower (which is indexed as (0, 0)).
 * There is one cup of excess liquid.
 * The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally,
 * and each will get half cup of champagne.
 *
 * Note:
 * 1.poured will be in the range of [0, 10 ^ 9].
 * 2.query_glass and query_row will be in the range of [0, 99].
 */

import java.lang.Double.max
import java.lang.Double.min

class Solution_champagne_tower {
    // a straightforward idea is to simulate the process
    // simulate by time is tricky and unnecessary
    // instead should simulate by total amount as we only care about final state
    // let dp[i][j] denotes ith row and jth glass
    // so it can accept half split from top left and half split from top right
    // considering the index it is: dp[i][j] = max(dp[i-1][j]-1,0)/2 + max(dp[i-1][j-1]-1,0)/2
    // of course if it's left most cup then j = 0 which makes j-1 not valid
    // what about right most? It depends on the size of dp[i-1].
    // then only two rows are involved, we can do rolling dp with top to bottom, right to left fashion

    // T:O(n^2) => O(1) S:O(n) => O(1) as n = 100
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        val t = MutableList(100, { 0.0 })
        t[0] = poured.toDouble()
        for (i in 1..query_row) {
            for (j in i downTo 0) {
                t[j] = if (j > 0) {
                    max(t[j] - 1, 0.0) / 2 + max(t[j - 1] - 1, 0.0) / 2
                } else {
                    max(t[j] - 1, 0.0) / 2
                }
            }
        }
        return min(1.0, t[query_glass])
    }
}

fun main(args: Array<String>) {
    // LC OJ passed
    val s = Solution_champagne_tower()
    println(s.champagneTower(1, 1, 1))
    println(s.champagneTower(2, 1, 1))
}