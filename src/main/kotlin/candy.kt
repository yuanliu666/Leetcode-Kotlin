/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 */

class Solution_candy {
    // T:O(n) S:O(n)
    fun getMinCandy(ratings: Array<Int>): Int {
        if (ratings.size == 1) {
            return 1
        }
        val candies = Array(ratings.size, { 1 })
        for (i in 0..ratings.size - 2) {
            if (ratings[i] < ratings[i + 1] && candies[i] >= candies[i + 1]) {
                candies[i + 1] = candies[i] + 1
            }
        }
        for (i in (ratings.size - 1).downTo(1)) {
            if (ratings[i] < ratings[i - 1] && candies[i] >= candies[i - 1]) {
                candies[i - 1] = candies[i] + 1
            }
        }
        return candies.sum()
    }
}

fun main(args: Array<String>) {
    val s = Solution_candy()
    println(s.getMinCandy(arrayOf(2)))
    println(s.getMinCandy(arrayOf(1, 2, 3)))
    println(s.getMinCandy(arrayOf(1, 2, 1)))
    println(s.getMinCandy(arrayOf(2, 1, 2)))
}