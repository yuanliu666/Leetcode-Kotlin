import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CompareVersionNumbersTest {

    @Test
    fun testCompareVersion() {
        val s = Solution_compare_version_numbers()
        assertThat(s.compareVersion("01", "1"), CoreMatchers.equalTo(0))
        assertThat(s.compareVersion("0.1", "1.1"), CoreMatchers.equalTo(-1))
        assertThat(s.compareVersion("1.0.1", "1"), CoreMatchers.equalTo(1))
        assertThat(s.compareVersion("7.5.2.4", "7.5.3"), CoreMatchers.equalTo(-1))
        assertThat(s.compareVersion("7", "7.0.0"), CoreMatchers.equalTo(0))

        assertThat(s.compareVersion1("01", "1"), CoreMatchers.equalTo(0))
        assertThat(s.compareVersion1("0.1", "1.1"), CoreMatchers.equalTo(-1))
        assertThat(s.compareVersion1("1.0.1", "1"), CoreMatchers.equalTo(1))
        assertThat(s.compareVersion1("7.5.2.4", "7.5.3"), CoreMatchers.equalTo(-1))
        assertThat(s.compareVersion1("7", "7.0.0"), CoreMatchers.equalTo(0))
    }
}