/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
class Solution_merge_k_sorted_lists {

    // Min heap solution. T:O(nlogk), S:O(n+k) as we create new nodes
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val arr = Array(lists.size) { i -> lists[i] }
        // Recode the value index pair
        val heap = PriorityQueue<IntArray>(compareBy { it[0] })
        val d = ListNode(-1)
        var h = d
        // Get first batch
        for (i in arr.indices) {
            if (arr[i] != null) {
                heap.offer(intArrayOf(arr[i]!!.`val`, i))
            }
        }
        // Get min and put in its next
        while (heap.isNotEmpty()) {
            val a = heap.poll()
            val v = a[0]
            val n = a[1]
            arr[n] = arr[n]?.next
            h.next = ListNode(v)
            h = h.next!!
            if (arr[n] != null) {
                heap.offer(intArrayOf(arr[n]!!.`val`, n))
            }
        }

        return d.next
    }


    // We could also save the node reference and reuse existed nodes instead of creating new nodes,
    // which will be T:O(nlogk) S:O(k)
    fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
        val arr = Array(lists.size) { i -> lists[i] }
        val heap = PriorityQueue<ListNode>(compareBy { it.`val` })
        val d = ListNode(-1)
        var h = d

        for (i in arr.indices) {
            if (arr[i] != null) {
                heap.offer(arr[i])
            }
        }

        while (heap.isNotEmpty()) {
            val a = heap.poll()
            val next = a.next
            a.next = null
            h.next = a
            h = h.next!!
            if (next != null) {
                heap.offer(next)
            }
        }

        return d.next
    }
}