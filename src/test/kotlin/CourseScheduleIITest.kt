import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CourseScheduleIITest {

    @Test
    fun testFindOrder() {
        val s = Solution_course_schedule_ii()
        assertThat(s.findOrder(2, arrayOf(intArrayOf(1, 0))),
                equalTo(intArrayOf(0, 1)))
        assertThat(s.findOrder(4, arrayOf(
                intArrayOf(1, 0),
                intArrayOf(2, 0),
                intArrayOf(3, 1),
                intArrayOf(3, 2)
        )), anyOf(equalTo(intArrayOf(0, 1, 2, 3)),
                equalTo(intArrayOf(0, 2, 1, 3))))
    }
}