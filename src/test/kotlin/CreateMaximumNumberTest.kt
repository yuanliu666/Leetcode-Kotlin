import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CreateMaximumNumberTest {

    @Test
    fun testMaxNumber() {
        val s = Solution_create_maximum_number()
        assertThat(s.maxNumber(intArrayOf(3, 4, 6, 5), intArrayOf(9, 1, 2, 5, 8, 3), 5),
                equalTo(intArrayOf(9, 8, 6, 5, 3)))
        assertThat(s.maxNumber(intArrayOf(6, 7), intArrayOf(6, 0, 4), 5),
                equalTo(intArrayOf(6, 7, 6, 0, 4)))
        assertThat(s.maxNumber(intArrayOf(3, 9), intArrayOf(8, 9), 3),
                equalTo(intArrayOf(9, 8, 9)))
    }
}