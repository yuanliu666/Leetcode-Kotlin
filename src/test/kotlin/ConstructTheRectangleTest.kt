import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConstructTheRectangleTest {

    @Test
    fun testConstructRectangle() {
        val s = Solution_construct_the_rectangle()
        val actual = s.constructRectangle(4)
        val expected = intArrayOf(2, 2)
        assertThat(actual, CoreMatchers.equalTo(expected))
    }
}