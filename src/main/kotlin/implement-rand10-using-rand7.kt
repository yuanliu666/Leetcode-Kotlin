/**
 * Given the API rand7() that generates a uniform random integer in the range [1, 7],
 * write a function rand10() that generates a uniform random integer in the range [1, 10].
 * You can only call the API rand7(), and you shouldn't call any other API.
 * Please do not use a language's built-in random API.
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing.
 * Note that this is not an argument passed to rand10().
 */
class Solution_implement_rand10_using_rand7 {

    // Leetcode has good solution for this one:
    // https://leetcode.com/problems/implement-rand10-using-rand7/solutions/151182/implement-rand10-using-rand7/
    // The core idea is to use a matrix to approach uniform distribution:
    // Let's say we have rand_a, rand_b, let a matrix of a*b, each element will be chance of 1/(a*b).
    // We know that for flatten 2D to 1D, we have idx=r*n+c, here let's say we rolled r=p and c=q, and a is total col number,
    // because they are 1 based, the index is (p-1)*a+(q-1), and since value is 1 based,
    // the corresponding number should plus 1: n = (p-1)*a+q
    // Then, if we assign cycles of 1~10 based on indexes, for complete cycles we will have evenly possibility distrubition.
    // That is, if n <= f*10, we cen get the result ret = 1 + (n - 1) % 10, because n is 1 based, need to minus 1,
    // then we need to mod 10 and add 1 since mode 10 give us result in [0,9].
    // Otherwise, we can repeat the process, or with optimization, using the remaining
    // as a new rand_remain to form a new matrix and continue.
    // T:O(1) average, O(∞) worst case, S:O(1)
    fun rand10(): Int {
        var a: Int
        var b: Int
        var idx: Int
        while (true) {
            a = rand7()
            b = rand7()
            idx = b + (a - 1) * 7
            if (idx <= 40) return 1 + (idx - 1) % 10
            // b is col, since we are based on 7, one side of matrix size is always 7, here 7 is always total col number
            a = idx - 40
            b = rand7()
            // get uniform dist from 1 - 63
            idx = b + (a - 1) * 7
            if (idx <= 60) return 1 + (idx - 1) % 10
            a = idx - 60
            b = rand7()
            // get uniform dist from 1 - 21
            idx = b + (a - 1) * 7
            if (idx <= 20) return 1 + (idx - 1) % 10
            // if we hit 1, need to start over
        }
    }

    // Dummy function
    private fun rand7(): Int {
        val random = kotlin.random.Random(0)
        return random.nextInt(1, 8)
    }
}