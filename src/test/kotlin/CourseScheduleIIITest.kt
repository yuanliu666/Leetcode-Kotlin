import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class CourseScheduleIIITest {

    @Test
    fun testScheduleCourse() {
        val s = Solution_course_schedule_iii()
        assertThat(s.scheduleCourse(arrayOf(
                intArrayOf(100, 200),
                intArrayOf(200, 1300),
                intArrayOf(1000, 1250),
                intArrayOf(2000, 3200)
        )), equalTo(3))
    }
}