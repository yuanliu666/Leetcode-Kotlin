import org.junit.Test

class ContiguousArrayTest {

    @Test
    fun testFindMaxLength() {
        val s = Solution_contiguous_array()
        assert(s.findMaxLength(intArrayOf(0, 1)) == 2)
        assert(s.findMaxLength(intArrayOf(0, 1, 0)) == 2)
        assert(s.findMaxLength(intArrayOf(0, 1, 1, 0, 1, 1, 1, 0)) == 4)
    }
}