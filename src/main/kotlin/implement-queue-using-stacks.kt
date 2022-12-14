/**
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 * Implement the MyQueue class:
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.
 * Notes:
 * You must use only standard operations of a stack, which means only push to top,
 * peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, the stack may not be supported natively.
 * You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 */
class Solution_implement_queue_using_stacks {

    class MyQueue() {

        private val s1 = Stack<Int>()
        private val s2 = Stack<Int>()

        // T:O(1)
        fun push(x: Int) {
            s2.push(x)
        }

        // T: amortized O(1) since we only need to move elements once
        fun pop(): Int {
            if (s1.isNotEmpty()) return s1.pop()
            while (s2.isNotEmpty()) {
                s1.push(s2.pop())
            }
            return s1.pop()
        }

        // Same as above
        fun peek(): Int {
            if (s1.isNotEmpty()) return s1.peek()
            while (s2.isNotEmpty()) {
                s1.push(s2.pop())
            }
            return s1.peek()
        }

        // T:o(1)
        fun empty(): Boolean {
            return s1.isEmpty() && s2.isEmpty()
        }
    }
}