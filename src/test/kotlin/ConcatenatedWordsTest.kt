import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConcatenatedWordsTest {

    @Test
    fun testFindAllConcatenatedWordsInADict() {
        val s = Solution_concatenated_words()
        val retSet1 = s.findAllConcatenatedWordsInADict(
                arrayOf("cat",
                        "cats",
                        "catsdogcats",
                        "dog",
                        "dogcatsdog",
                        "hippopotamuses",
                        "rat",
                        "ratcatdogcat"))
                .toSet()
        val retSet2 = s.findAllConcatenatedWordsInADict2(
                arrayOf("cat",
                        "cats",
                        "catsdogcats",
                        "dog",
                        "dogcatsdog",
                        "hippopotamuses",
                        "rat",
                        "ratcatdogcat"))
                .toSet()
        assertThat(retSet1, CoreMatchers.equalTo(retSet2))
        assertThat(retSet1, CoreMatchers.equalTo(setOf("catsdogcats", "dogcatsdog", "ratcatdogcat")))
    }
}