import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import utils.TreeNode

class CountUnivalueSubtreesTest {

    @Test
    fun testCountUnivalSubtrees() {
        val s = Solution_count_univalue_subtrees()
        // only 1 root node should count 1
        val tree1 = TreeNode(1)
        assertThat(s.countUnivalSubtrees(tree1), CoreMatchers.equalTo(1))
        //   1
        //  / \
        // 1  1  should count 3
        val tree2 = TreeNode(1)
        tree2.left = TreeNode(1)
        tree2.right = TreeNode(1)
        assertThat(s.countUnivalSubtrees(tree2), CoreMatchers.equalTo(3))
        //   1
        //  / \
        // 2  1  should count 2
        val tree3 = TreeNode(1)
        tree3.left = TreeNode(2)
        tree3.right = TreeNode(1)
        assertThat(s.countUnivalSubtrees(tree3), CoreMatchers.equalTo(2))
        //   2
        //  / \
        // 1  1  should count 2
        val tree4 = TreeNode(2)
        tree4.left = TreeNode(1)
        tree4.right = TreeNode(1)
        assertThat(s.countUnivalSubtrees(tree4), CoreMatchers.equalTo(2))
        // example in question
        val tree5 = TreeNode(5)
        tree5.left = TreeNode(1)
        tree5.left?.left = TreeNode(5)
        tree5.left?.right = TreeNode(5)
        tree5.right = TreeNode(5)
        tree5.right?.right = TreeNode(5)
        assertThat(s.countUnivalSubtrees(tree5), CoreMatchers.equalTo(4))
        //  2
        //   \
        //    1  should count 1
        val tree6 = TreeNode(2)
        tree6.right = TreeNode(1)
        assertThat(s.countUnivalSubtrees(tree6), CoreMatchers.equalTo(1))
    }
}