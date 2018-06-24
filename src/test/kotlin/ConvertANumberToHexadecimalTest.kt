import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConvertANumberToHexadecimalTest {

    @Test
    fun testToHex() {
        val s = Solution_convert_a_number_to_hexadecimal()
        assertThat(s.toHex(26), CoreMatchers.equalTo("1a"))
        assertThat(s.toHex(-1), CoreMatchers.equalTo("ffffffff"))
    }
}