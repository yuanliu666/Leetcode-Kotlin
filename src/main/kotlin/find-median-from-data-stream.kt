/**
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value, and the median is the mean of the two middle values.

 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Answers within 10^-5 of the actual answer will be accepted.
 *
 * Constraints:
 * -10^5 <= num <= 10^5
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 10^4 calls will be made to addNum and findMedian.
 * Follow up:
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
class MedianFinder() {

    private val minHeap = PriorityQueue<Int>()
    private val maxHeap = PriorityQueue<Int>(compareBy { -it })

    // Two heaps solution. Keep lower half in maxHeap and upper half in minHeap, balance size on the go.
    // T:O(logn)
    fun addNum(num: Int) {
        minHeap.offer(num)
        maxHeap.offer(minHeap.poll())
        if (maxHeap.size > minHeap.size) {
            minHeap.offer(maxHeap.poll())
        }
    }

    // T:O(logn)
    fun findMedian(): Double {
        return when {
            minHeap.size > maxHeap.size -> minHeap.peek().toDouble()
            else -> (minHeap.peek() + maxHeap.peek()) / 2.0
        }
    }
}