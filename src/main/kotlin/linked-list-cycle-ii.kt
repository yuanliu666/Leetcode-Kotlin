import utils.ListNode

/**
 * Given the head of a linked list, return the node where the cycle begins.
 * If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed).
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * Do not modify the linked list.
 *
 * The number of the nodes in the list is in the range [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * pos is -1 or a valid index in the linked-list.
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
class Solutioin_linked_list_cycle_ii {

    // T:O(n) S:O(n)
    fun detectCycle(head: ListNode?): ListNode? {
        val set = hashSetOf<ListNode>()
        var cur = head
        while (cur?.next != null) {
            set.add(cur)
            if (set.contains(cur.next!!)) {
                return cur.next
            }
            cur = cur.next
        }
        return null
    }

    // Use fast slow pointers
    // T:O(n) S:O(1)
    // Let's say the distance from head to cycle start point is A,
    // the distance that slow and fast meet from cycle start is B, and the cycle length is C,
    // We have:
    // distance of slow went: A + B
    // distance of fast went: A + B + n*C
    // and: (A + B) * 2 = A + B + n*C
    // => A + B = n*C
    // So in order to move to cycle start, which should be distance of A or A + n*C, we can move slow by another A,
    // then it will be A + B + A = A + n*C.
    // To do this, we send another slow pointer from start, and after distance of A, they will meet at cycle start.
    fun detectCycleFollowUp(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (slow == fast) {
                break
            }
        }
        if (fast?.next == null) return null
        var p = head
        while (p != slow) {
            p = p?.next
            slow = slow?.next
        }
        return slow
    }
}