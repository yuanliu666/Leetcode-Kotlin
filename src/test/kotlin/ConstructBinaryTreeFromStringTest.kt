import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import utils.isLeaf
import utils.isTreeEqualTo

class ConstructBinaryTreeFromStringTest {

    @Test
    fun testConstructTree() {
        val s = Solution_construct_binary_tree_from_string()
        val ret1 = s.constructTree("4(2(3)(1))(6(5))")
        val ret2 = s.constructTree2("4(2(3)(1))(6(5))")

        assert(ret1.isTreeEqualTo(ret2))

        assertThat(ret1?.value, CoreMatchers.equalTo(4))
        assertThat(ret1?.left?.value, CoreMatchers.equalTo(2))
        assertThat(ret1?.left?.left?.value, CoreMatchers.equalTo(3))
        assertThat(ret1?.left?.right?.value, CoreMatchers.equalTo(1))
        assert(ret1?.left?.left?.isLeaf() ?: false)
        assert(ret1?.left?.right?.isLeaf() ?: false)
        assertThat(ret1?.right?.value, CoreMatchers.equalTo(6))
        assertThat(ret1?.right?.left?.value, CoreMatchers.equalTo(5))
        assert(ret1?.right?.right == null)
        assert(ret1?.right?.left?.isLeaf() ?: false)
    }
}