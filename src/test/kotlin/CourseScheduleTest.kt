import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CourseScheduleTest {

    @Test
    fun testCanFinish() {
        val s = Solution_course_schedule()
        assertTrue(s.canFinish(2, arrayOf(intArrayOf(1, 0))))
        assertFalse(s.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
    }
}
