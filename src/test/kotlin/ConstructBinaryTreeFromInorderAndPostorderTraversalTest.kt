import org.junit.Test
import utils.isLeaf

class ConstructBinaryTreeFromInorderAndPostorderTraversalTest {

    @Test
    fun testBuildTree() {
        val s = Solution_construct_binary_tree_from_inorder_and_postorder_traversal()
        val r = s.buildTree(intArrayOf(9, 3, 15, 20, 7), intArrayOf(9, 15, 7, 20, 3))
        assert(r?.value == 3)
        assert(r?.left?.value == 9)
        assert(r?.left?.isLeaf() == true)
        assert(r?.right?.value == 20)
        assert(r?.right?.left?.value == 15)
        assert(r?.right?.left?.isLeaf() == true)
        assert(r?.right?.right?.value == 7)
        assert(r?.right?.right?.isLeaf() == true)
    }
}