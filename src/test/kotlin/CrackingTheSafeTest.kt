import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class CrackingTheSafeTest {

    @Test
    fun testCrackingSafe() {
        val s = Solution_cracking_the_safe()
        assertThat(s.crackSafe(1, 2),
                anyOf(equalTo("10"), equalTo("01")))
        assertThat(s.crackSafe(2, 2),
                anyOf(equalTo("00110"), equalTo("10011"), equalTo("11001")))
    }
}