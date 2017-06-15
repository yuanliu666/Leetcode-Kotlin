package utils

class ListNode constructor(var value: Int = -1, var next: ListNode? = null) {
    override fun toString(): String {
        return "$value -> ${next.toString()}"
    }

    companion object {
        // a help function to generate a linked list with given values quickly, for test purpose only
        fun quickList(nodes: List<Int>): ListNode {
            val dummy = ListNode()
            nodes.reversed().forEach({
                val temp = ListNode(it)
                temp.next = dummy.next
                dummy.next = temp
            })
            return dummy.next!!
        }
    }
}