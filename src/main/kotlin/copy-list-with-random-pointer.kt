/**
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 */

class Solution_copy_list_with_random_pointer {

    data class RandomListNode(var value: Int = 0, var next: RandomListNode? = null, var random: RandomListNode? = null)

    // T:O(n) S:O(n)
    fun copyRandomList(head: RandomListNode?): RandomListNode? {
        val dummy = RandomListNode()
        var cur: RandomListNode? = head
        var prev: RandomListNode? = dummy
        // original node to copied node map
        val copies = mutableMapOf<RandomListNode, RandomListNode?>()
        // first pass, copy main list
        while (cur != null) {
            val copied = RandomListNode(cur.value)
            copies[cur] = copied
            prev?.next = copied
            prev = prev?.next
            cur = cur.next
        }
        // second pass, copy random
        cur = head
        while (cur != null) {
            cur.let { current ->
                current.random?.let {
                    copies[current]?.random = copies[it]
                }
            }
            cur = cur.next
        }
        return dummy.next
    }

    // T:O(n) S:O(1)
    fun copyRandomList2(head: RandomListNode?): RandomListNode? {
        var cur: RandomListNode? = head
        // copy list in place
        while (cur != null) {
            val copied = RandomListNode(cur.value)
            copied.next = cur.next
            cur.next = copied
            cur = copied.next
        }
        // update random
        cur = head
        while (cur != null) {
            cur.let { current ->
                current.random?.let {
                    current.next?.random = it.next
                }
            }
            cur = cur.next?.next
        }
        // split
        val dummy = RandomListNode()
        var copy: RandomListNode? = dummy
        cur = head
        while (cur != null) {
            copy?.next = cur.next
            cur.next = cur.next?.next
            copy = copy?.next
            cur = cur.next
        }
        return dummy.next
    }
}

fun main(args: Array<String>) {
    // LC does not have Kotlin version for this problem yet
    // see [CopyListWithRandomPointerTest] for unit test
}