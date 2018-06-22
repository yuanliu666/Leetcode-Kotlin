import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContinuousSubarraySumTest {

    @Test
    fun testCheckSubarraySum() {
        val s = Solution_continuous_subarray_sum()
        assertTrue(s.checkSubarraySum(intArrayOf(23, 2, 4, 6, 7), -6))
        assertTrue(s.checkSubarraySum(intArrayOf(23, 2, 6, 4, 7), 6))
        assertTrue(s.checkSubarraySum(intArrayOf(0, 0), -1))
        assertFalse(s.checkSubarraySum(intArrayOf(1, 2, 3), 0))
        assertTrue(s.checkSubarraySum(intArrayOf(100, 100), 100))
    }
}