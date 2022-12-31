/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
 * evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
class LRUCache(capacity: Int) {

    // A gotcha is that, put operation is also considered as "used".
    // This solution is based on double linked list with a map.
    // Why? Because we want:
    // 1. Access to key-value quickly, where a map is useful;
    // 2. Remove least used entry. This one can be solved in O(1) with double linked list:
    //      2.1 Let map pointing to nodes, therefore we can get the node easily;
    //      2.2 For fast move or delete, introduce head and tail dummy nodes;
    //      2.3 When removing node, also need to update map, which will need the key. An easy solution is to
    //          also save the key in the node.
    // All operations are in O(1) time. Space is O(n) since we created n nodes.
    private val cap = capacity
    private val map = hashMapOf<Int, DoubleListNode>()
    private val head = DoubleListNode(-1, -1)
    private val tail = DoubleListNode(-1, -1).also {
        it.prev = head
        head.next = it
    }
    private var cnt = 0

    class DoubleListNode(val key: Int, var value: Int) {
        var prev: DoubleListNode? = null
        var next: DoubleListNode? = null
    }

    fun get(key: Int): Int {
        if (!map.containsKey(key)) return -1
        val node: DoubleListNode = map[key]!!
        moveNodeToHead(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (map.containsKey(key)) {
            val n: DoubleListNode = map[key]!!
            n.value = value
            moveNodeToHead(n)
        } else {
            cnt++
            if (cnt > cap) {
                val r = removeNodeAtTail()
                map.remove(r.key)
                cnt--
            }
            val n = DoubleListNode(key, value)
            map[key] = n
            insertNodeAtHead(n)
        }
    }

    private fun moveNodeToHead(n: DoubleListNode) {
        if (head.next === n) return
        val prev: DoubleListNode = n.prev!!
        val after: DoubleListNode = n.next!!
        val h: DoubleListNode = head.next!!

        prev.next = after
        after.prev = prev
        head.next = n
        n.prev = head
        n.next = h
        h.prev = n
    }

    private fun insertNodeAtHead(n: DoubleListNode) {
        val h = head.next
        head.next = n
        n.prev = head
        n.next = h
        h?.prev = n
    }

    private fun removeNodeAtTail(): DoubleListNode {
        val nodeToRemove: DoubleListNode = tail.prev!!
        val n: DoubleListNode = nodeToRemove.prev!!
        n.next = tail
        tail.prev = n
        return nodeToRemove
    }
}