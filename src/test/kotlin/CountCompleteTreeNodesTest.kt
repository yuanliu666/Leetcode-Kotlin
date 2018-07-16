import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import utils.TreeNode

class CountCompleteTreeNodesTest {

    @Test
    fun testCountNodes() {
        val s = Solution_count_complete_tree_nodes()
        val root = TreeNode(1)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(1))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(1))
        root.left = TreeNode(2)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(2))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(2))
        root.right = TreeNode(3)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(3))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(3))
        root.left?.left = TreeNode(4)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(4))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(4))
        root.left?.right = TreeNode(5)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(5))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(5))
        root.right?.left = TreeNode(6)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(6))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(6))
        root.right?.right = TreeNode(7)
        assertThat(s.countNodes(root), CoreMatchers.equalTo(7))
        assertThat(s.countNodes2(root), CoreMatchers.equalTo(7))
    }
}