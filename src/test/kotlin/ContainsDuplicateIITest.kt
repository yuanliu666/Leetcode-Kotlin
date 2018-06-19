import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContainsDuplicateIITest {

    @Test
    fun testContainsNearbyDuplicate() {
        val s = Solution_contains_duplicate_ii()
        assertTrue(s.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3))
        assertTrue(s.containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1))
        assertFalse(s.containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2))
    }
}