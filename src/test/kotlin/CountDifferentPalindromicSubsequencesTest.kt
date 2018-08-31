import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountDifferentPalindromicSubsequencesTest {

    @Test
    fun testCountPalindromicSubsequences() {
        val s = Solution_count_different_palindromic_subsequences()
        assertThat(s.countPalindromicSubsequences("bccb"), CoreMatchers.equalTo(6))
        assertThat(s.countPalindromicSubsequences("abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba"), CoreMatchers.equalTo(104860361))
    }
}