import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import utils.TreeNode

class ConstructStringFromBinaryTreeTest {

    @Test
    fun testTree2Str() {
        val s = Solution_construct_string_from_binary_tree()
        val r1 = TreeNode(1)
        r1.left = TreeNode(2)
        r1.right = TreeNode(3)
        r1.left?.left = TreeNode(4)
        assertThat(s.tree2str(r1), CoreMatchers.equalTo("1(2(4))(3)"))

        val r2 = TreeNode(1)
        r2.left = TreeNode(2)
        r2.right = TreeNode(3)
        r2.left?.right = TreeNode(4)
        assertThat(s.tree2str(r2), CoreMatchers.equalTo("1(2()(4))(3)"))
    }
}