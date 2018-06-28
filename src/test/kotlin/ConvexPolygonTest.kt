import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ConvexPolygonTest {

    @Test
    fun testIsConvexPolygon() {
        val s = Solution_convex_polygon()
        assertTrue(s.isConvexPolygon(listOf(listOf(0, 0), listOf(0, 1), listOf(1, 1), listOf(1, 0))))
        assertFalse(s.isConvexPolygon(listOf(listOf(0, 0), listOf(0, 10),
                listOf(10, 10), listOf(10, 0), listOf(5, 5))))
    }
}