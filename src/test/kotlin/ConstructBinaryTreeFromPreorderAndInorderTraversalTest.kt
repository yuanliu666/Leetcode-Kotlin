import org.junit.Test
import utils.isLeaf

class ConstructBinaryTreeFromPreorderAndInorderTraversalTest {

    @Test
    fun testBuildTree() {
        val r = Solution_construct_binary_tree_from_preorder_and_inorder_traversal()
                .buildTree(intArrayOf(3, 9, 20, 15, 7), intArrayOf(9, 3, 15, 20, 7))
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