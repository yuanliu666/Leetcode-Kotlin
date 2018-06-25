import org.junit.Assert.assertTrue
import org.junit.Test
import utils.TreeNode

class ConvertBstToGreaterTreeTest {

    @Test
    fun testConvertBst() {
        val s = Solution_convert_bst_to_greater_tree()
        val r1 = TreeNode(5)
        r1.left = TreeNode(2)
        r1.right = TreeNode(13)
        val ret1 = s.convertBST(r1)
        assertTrue(ret1?.value == 18)
        assertTrue(ret1?.left?.value == 20)
        assertTrue(ret1?.right?.value == 13)

        val r2 = TreeNode(5)
        r2.left = TreeNode(2)
        r2.right = TreeNode(13)
        val ret2 = s.convertBSTRec(r2)
        assertTrue(ret2?.value == 18)
        assertTrue(ret2?.left?.value == 20)
        assertTrue(ret2?.right?.value == 13)
    }
}