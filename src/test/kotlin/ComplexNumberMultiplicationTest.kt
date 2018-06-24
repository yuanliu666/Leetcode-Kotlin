import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ComplexNumberMultiplicationTest {

    @Test
    fun testComplexNumberMultiply() {
        val s = Solution_complex_number_multiplication()
        assertThat(s.complexNumberMultiply("1+1i", "1+1i"), CoreMatchers.equalTo("0+2i"))
        assertThat(s.complexNumberMultiply("1+-1i", "1+-1i"), CoreMatchers.equalTo("0+-2i"))
    }
}