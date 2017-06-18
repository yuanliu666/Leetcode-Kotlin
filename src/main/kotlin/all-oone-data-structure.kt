/**
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
 * Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure.
 * Otherwise decrements an existing key by 1. If the key does not exist,
 * this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */

// To solve this problem, we need a double linked list
class Node(var value: Int, var keys: HashSet<String>, var prev: Node? = null, var next: Node? = null) {
    override fun toString(): String {
        return "($value, [${keys.joinToString()}]) <=> $next"
    }
}

class DoubleLinkedList {
    // The values of inserted nodes should always be greater than 0
    // We need both head and tail to access min/max quickly, and try to keep values ordered
    val head = Node(0, hashSetOf())
    val tail = Node(0, hashSetOf())

    init {
        head.next = tail
        tail.prev = head
    }

    // insert node BEFORE target
    fun insert(target: Node, node: Node): Node {
        node.prev = target.prev
        node.next = target
        target.prev?.next = node
        target.prev = node
        return node
    }

    // erase a node from linked list
    fun erase(node: Node) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    // return whether this linked list is empty
    fun isEmpty(): Boolean {
        return head.next === tail
    }

    // return the begin node after head
    fun begin(): Node {
        return head.next!!
    }

    // return the end node before tail
    fun end(): Node {
        return tail.prev!!
    }

    override fun toString(): String {
        return head.toString()
    }
}

// T:O(1) S:O(k)
class Solution_all_oone_data_structure {
    val bucket_of_key = hashMapOf<String, Node>()
    val buckets = DoubleLinkedList()

    // insert a key of 1 or increment a key by 1
    fun inc(key: String) {
        // if there is no such key, insert 0 after beginning
        // why insert 0? To merge two cases
        // case 1:
        // key does not exist, we check if value 1 node exists. If yes, add it; otherwise insert new node
        // case 2:
        // key already exists, we want to check if corresponding value + 1 exists or not
        // if it exists, add key to it, and remove key from previous node, erase it if key set becomes empty
        // if it does not exist, insert a new node, and do the erase check thing
        // we can handle two cases separately, but they can be merged:
        // put a node of 0 if key does not exist, and then follow the logic of case 2
        // this 0 node will be erased anyway
        if (key !in bucket_of_key)
            bucket_of_key.put(key, buckets.insert(buckets.begin(), Node(0, hashSetOf())))

        // get corresponding node and its next
        val bucket = bucket_of_key[key]
        var next_bucket = bucket?.next

        // if there is no value + 1 node, insert new one
        if (next_bucket == buckets.tail || (next_bucket!!.value > bucket!!.value + 1))
            next_bucket = buckets.insert(next_bucket, Node(bucket!!.value + 1, hashSetOf()))

        // adjust keys
        next_bucket.keys.add(key)
        bucket_of_key[key] = next_bucket
        bucket.keys.remove(key)

        // remove node with empty key set
        if (bucket.keys.isEmpty())
            buckets.erase(bucket)
    }

    fun dec(key: String) {
        // if key not present, cannot do anything
        if (key !in bucket_of_key)
            return

        val bucket = bucket_of_key[key]
        var prev_bucket = bucket?.prev

        // if it turns out that value > 1, we add it back with value - 1 node
        bucket_of_key.remove(key)

        // if value is 1, we know there is no 0 node (head and tail do not count)
        if (bucket!!.value > 1) {
            // if value - 1 node does not exist, insert new one
            if (bucket == buckets.begin() || prev_bucket!!.value < bucket.value - 1)
                prev_bucket = buckets.insert(bucket, Node(bucket.value - 1, hashSetOf()))

            prev_bucket.keys.add(key)
            bucket_of_key.put(key, prev_bucket)
        }
        bucket.keys.remove(key)
        if (bucket.keys.isEmpty())
            buckets.erase(bucket)
    }

    fun getMaxKey(): String {
        if (buckets.isEmpty())
            return ""
        else
            return buckets.end().keys.first()
    }

    fun getMinKey(): String {
        if (buckets.isEmpty())
            return ""
        else
            return buckets.begin().keys.first()
    }

    override fun toString(): String {
        return buckets.toString()
    }
}

fun main(args: Array<String>) {
    val s = Solution_all_oone_data_structure()
    val a = "a"
    val b = "b"
    val c = "c"
    fun quickInc(key: String, n: Int) = (0..n - 1).forEach { s.inc(key) }
    fun quickDec(key: String, n: Int) = (0..n - 1).forEach { s.dec(key) }

    quickInc(a, 1)
    quickInc(b, 2)
    quickInc(c, 3)
    println(s)
    println(s.getMaxKey())
    println(s.getMinKey())
    quickInc(a, 1)
    quickDec(c, 1)
    println(s)
    println(s.getMaxKey())
    println(s.getMinKey())
    quickDec(a, 2)
    quickDec(b, 2)
    quickDec(c, 2)
    println(s)
    println(s.getMaxKey())
    println(s.getMinKey())
}
