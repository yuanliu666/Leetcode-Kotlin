import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContainsDuplicateIIITest {

    @Test
    fun testContainsNearbyAlmostDuplicate() {
        val s = Solution_contains_duplicate_iii()
        assertTrue(s.containsNearbyAlmostDuplicate(intArrayOf(1, 2, 3, 1), 3, 0))
        assertTrue(s.containsNearbyAlmostDuplicate(intArrayOf(1, 0, 1, 1), 1, 2))
        assertFalse(s.containsNearbyAlmostDuplicate(intArrayOf(1, 5, 9, 1, 5, 9), 2, 3))
        assertFalse(s.containsNearbyAlmostDuplicate(intArrayOf(-1, 2147463647), 1, 2147463647))
    }
}