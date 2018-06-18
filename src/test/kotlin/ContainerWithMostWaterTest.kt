import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ContainerWithMostWaterTest {

    @Test
    fun testMaxArea() {
        val s = Solution_container_with_most_water()
        assertThat(s.maxArea(intArrayOf(1, 3, 7, 4)), CoreMatchers.equalTo(6))
    }
}