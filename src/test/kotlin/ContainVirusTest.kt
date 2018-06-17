import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ContainVirusTest {

    @Test
    fun testContainVirus() {
        val s = Solution_contain_virus()
        val grid1 = arrayOf(
                intArrayOf(0, 1, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 1, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 0, 0, 0, 0, 0, 0))
        assertThat(s.containVirus(grid1), CoreMatchers.equalTo(10))
        val grid2 = arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 1),
                intArrayOf(1, 1, 1))
        assertThat(s.containVirus(grid2), CoreMatchers.equalTo(4))
        val grid3 = arrayOf(
                intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0),
                intArrayOf(1, 0, 1, 0, 1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 0))
        assertThat(s.containVirus(grid3), CoreMatchers.equalTo(13))
    }
}