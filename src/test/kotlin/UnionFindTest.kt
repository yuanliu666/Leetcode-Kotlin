import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class UnionFindTest {

    @Test
    fun testUnionFind() {
        val u1 = UnionFind(5)
        // union of [0, 1, 2] and [3, 4]
        u1.union(0, 1)
        u1.union(1, 2)
        u1.union(3, 4)
        assertThat(u1.find(0), CoreMatchers.equalTo(2))
        assertThat(u1.find(1), CoreMatchers.equalTo(2))
        assertThat(u1.find(2), CoreMatchers.equalTo(2))
        assertThat(u1.find(3), CoreMatchers.equalTo(4))
        assertThat(u1.find(4), CoreMatchers.equalTo(4))
        assertThat(u1.count, CoreMatchers.equalTo(2))
    }
}