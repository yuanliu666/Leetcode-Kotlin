/**
 * You are given a doubly linked list, which contains nodes that have a next pointer,
 * a previous pointer, and an additional child pointer.
 * This child pointer may or may not point to a separate doubly linked list, also containing these special nodes.
 * These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure as shown in the example below.
 * Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level,
 * doubly linked list. Let curr be a node with a child list.
 * The nodes in the child list should appear after curr and before curr.next in the flattened list.
 * Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
 */

class Solution_flatten_a_multilevel_doubly_linked_list {

    class Node(var `val`: Int) {
        var prev: Node? = null
        var next: Node? = null
        var child: Node? = null
    }

    // Recursion, T:O(n) S:O(h)
    fun flatten(root: Node?): Node? {
        return helper(root).first
    }

    // Return head tail pair
    private fun helper(root: Node?): Pair<Node?, Node?> {
        var node = root
        var tail = node
        // make sure single node still runs
        while (node != null) {
            tail = node
            if (node.child != null) {
                val child = helper(node.child)
                val childHead = child.first
                val childTail = child.second
                val next = node.next

                node.child = null
                node.next = childHead
                childHead?.prev = node
                childTail?.next = next
                next?.prev = childTail

                node = next
                // handle tail is child case
                if (next == null) {
                    tail = childTail
                }
            } else {
                node = node.next
            }
        }
        return Pair(root, tail)
    }
}