/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
class Solution_min_stack {

    // T:O(1) S:O(n)
    private val stk = Stack<Int>()
    private val minStk = Stack<Int>()

    fun push(`val`: Int) {
        stk.push(`val`)
        if (minStk.isEmpty() || `val` <= minStk.peek()) {
            minStk.push(`val`)
        }
    }

    fun pop() {
        val n = stk.pop()
        if (n == minStk.peek()) minStk.pop()
    }

    fun top(): Int {
        return stk.peek()
    }

    fun getMin(): Int {
        return minStk.peek()
    }
}