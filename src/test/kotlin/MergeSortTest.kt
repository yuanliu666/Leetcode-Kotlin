import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MergeSortTest {

    @Test
    fun testMergeSort() {
        val s = MergeSort()
        assertThat(s.mergeSort(intArrayOf()), CoreMatchers.equalTo(intArrayOf()))
        assertThat(s.mergeSort(intArrayOf(1)), CoreMatchers.equalTo(intArrayOf(1)))
        assertThat(s.mergeSort(intArrayOf(2, 1)), CoreMatchers.equalTo(intArrayOf(1, 2)))
        assertThat(s.mergeSort(intArrayOf(6, 7, 1, 4, 3, 1)), CoreMatchers.equalTo(intArrayOf(1, 1, 3, 4, 6, 7)))
    }
}