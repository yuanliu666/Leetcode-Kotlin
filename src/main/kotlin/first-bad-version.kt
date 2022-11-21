/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
class VersionControl {
    // dummy function
    fun isBadVersion(version: Int): Boolean {
        return true
    }
}

class Solution_first_bad_version : VersionControl() {

    // T:O(logN*k), k is time complexity of check bad version, S:O(1)
    override fun firstBadVersion(n: Int): Int {
        var lo = 0
        var hi = n - 1
        while (lo < hi) {
            val mid = (hi - lo) / 2 + lo
            if (isBadVersion(mid + 1)) {
                hi = mid
            } else {
                lo = mid + 1
            }
        }
        return lo + 1
    }
}