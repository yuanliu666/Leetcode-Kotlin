/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 *
 * Example 1:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 *
 * Example 2:
 *
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 *
 * Example 3:
 *
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 */

class Solution_compare_version_numbers {

    // T:O(n) S:O(1)
    fun compareVersion(version1: String, version2: String): Int {
        var p1 = 0
        var p2 = 0
        var v1: Long
        var v2: Long
        while (p1 < version1.length || p2 < version2.length) {
            v1 = 0L
            v2 = 0L
            while (p1 < version1.length && version1[p1].isDigit()) {
                // warning here: use char.toLong() directly will return its ASCII number instead of desired value!
                v1 = v1 * 10 + version1[p1].toString().toLong()
                p1++
            }
            while (p2 < version2.length && version2[p2].isDigit()) {
                v2 = v2 * 10 + version2[p2].toString().toLong()
                p2++
            }
            if (v1 != v2) {
                return v1.compareTo(v2)
            } else {
                p1++
                p2++
            }
        }
        return 0
    }

    // T:O(n) S:O(n)
    fun compareVersion1(version1: String, version2: String): Int {
        // let version1.length <= version2.length
        if (version1.length > version2.length) {
            return -compareVersion(version2, version1)
        }

        val v1 = version1.split(".").toMutableList()
        val v2 = version2.split(".")
        for (i in 0 until (v2.size - v1.size)) {
            v1.add("0")
        }
        for (i in 0 until v2.size) {
            if (v1[i] != v2[i]) {
                return (v1[i].toInt()).compareTo(v2[i].toInt())
            }
        }
        return 0
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // see [CompareVersionNumbersTest] for unit test
}