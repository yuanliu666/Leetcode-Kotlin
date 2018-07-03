import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CountAndSayTest {

    @Test
    fun testCountAndSay() {
        val s = Solution_count_and_say()
        assertThat(s.countAndSay(1), CoreMatchers.equalTo("1"))
        assertThat(s.countAndSay(2), CoreMatchers.equalTo("11"))
        assertThat(s.countAndSay(3), CoreMatchers.equalTo("21"))
        assertThat(s.countAndSay(4), CoreMatchers.equalTo("1211"))
        assertThat(s.countAndSay(5), CoreMatchers.equalTo("111221"))
    }
}