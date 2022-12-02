/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 */
class Solution_serialize_and_deserialize_bst {

    // Encodes a tree to a single string.
    // No null symbols. T:O(n) S:O(h)
    fun serialize(root: TreeNode?): String {
        if (root == null) return ""
        val left = serialize(root.left)
        val right = serialize(root.right)
        var ret = root.`val`.toString()
        if (left.isNotEmpty()) {
            ret += ",${left}"
        }
        if (right.isNotEmpty()) {
            ret += ",${right}"
        }
        return ret
    }

    // Decodes your encoded data to tree.
    // T:O(n) S:O(n)
    fun deserialize(data: String): TreeNode? {
        if (data.isEmpty()) return null
        val list = data.split(',')
        return constructTree(list, 0, list.size)
    }

    // We will create N nodes. For binary search part, because each step the size will be reduced, it's not nlogn,
    // More like logn + loglogn +...
    private fun constructTree(list: List<String>, left: Int, right: Int): TreeNode? {
        if (left >= right) return null
        val root = TreeNode(list[left].toInt())
        if (right == left + 1) return root

        if (list[right - 1].toInt() < root.`val`) {
            // Only have left child tree
            root.left = constructTree(list, left + 1, right)
        } else if (list[left + 1].toInt() > root.`val`) {
            // Only have right child tree
            root.right = constructTree(list, left + 1, right)
        } else {
            // Use binary search to find first right child
            var lo = left + 1
            var hi = right
            while (lo < hi) {
                val m = (hi - lo) / 2 + lo
                if (list[m].toInt() < root.`val`) {
                    lo = m + 1
                } else {
                    hi = m
                }
            }
            root.left = constructTree(list, left + 1, lo)
            root.right = constructTree(list, lo, right)
        }

        return root
    }
}