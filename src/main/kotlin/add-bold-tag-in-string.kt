/**
 * Given a string s and a list of strings dict,
 * you need to add a closed pair of bold tag <b> and </b>
 * to wrap the substrings in s that exist in dict.
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 * Example 1:
 *
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 *
 * Example 2:
 *
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 *
 *
 * Note:
 *
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 */

class Solution_add_bold_tag_in_string {
    //================== My original solution
    // T:O(n*m*l + mlogm)
    // where n is the length of string, m is the number of string in dict, l is average length of strings.
    // S:O(m)
    fun addTags(s: String, dict: Array<String>): String {
        return insertTags(s, mergeIntervals(getIntervals(s, dict)))
    }

    // T: O(n), S:O(1)
    fun insertTags(s: String, list: List<Pair<Int, Int>>): String {
        var i = 0
        var j = 0
        var ret = ""
        // append intervals
        while (i < s.length && j < list.size) {
            val start = list[j].first + 1
            val end = list[j].second - 1
            if (i == start) {
                ret += "<b>${s.slice(start..end)}</b>"
                i = end
                j++
            } else {
                ret += s[i]
            }
            i++
        }
        // append rest
        ret += s.slice(i..s.length - 1)
        return ret
    }

    // Get list of interval pairs with (start - 1, end + 1) index of dict string in s.
    // why (start - 1, end + 1), not just (start, end)? That's for merge purpose when two intervals are adjacent.
    // For example, "ab bc" should be merged, but cannot merge with (0,1) and (2,3).
    // T: O(n*m*l)
    // S:O(m)
    fun getIntervals(s: String, dict: Array<String>): MutableList<Pair<Int, Int>> {
        val ret = mutableListOf<Pair<Int, Int>>()
        dict.forEach {
            val start = s.indexOf(it)
            val end = start + it.length - 1
            ret.add(Pair(start - 1, end + 1))
        }
        return ret
    }

    // T: O(mlogm), S:O(m)
    fun mergeIntervals(list: MutableList<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
        list.sortBy {
            it.first
        }
        var i = 0
        var validSize = list.size
        while (i < list.size - 1) {
            val p1 = list[i]
            val p2 = list[i + 1]
            val isCrossed = p2.first <= p1.second
            i++
            if (isCrossed) {
                // merge
                list[i] = Pair(p1.first, maxOf(p1.second, p2.second))
                validSize--
            }
        }
        return list.subList(list.size - validSize, list.size)
    }

    //================== Another slick solution
    // My original solution uses sort to help merging intervals, but actually this can be avoided.
    // When merging intervals we do not care about range, or string size in this problem.
    // However we still need to scan whole string to insert.
    // So instead of using intervals and merge them, we use "buckets".
    // Namely get a list of size n, set true if this char is covered by strings in dict.
    // This way we do not need care about ordering.
    // Why this is not the best solution for merging intervals only? Because we would have to extract from buckets.
    // But here we do not need to. Just scan string once.
    //T:O(n*m*l) S:O(n)
    fun addTags2(s: String, dict: Array<String>): String {
        val buckets = Array(s.length, { false })
        dict.forEach {
            val start = s.indexOf(it)
            val end = start + it.length - 1
            // set true
            IntRange(start, end).forEach {
                buckets[it] = true
            }
        }

        var ret = ""
        var prev = false
        var i = 0
        while (i < buckets.size) {
            if (buckets[i] != prev) {
                // edge found
                if (prev) {
                    ret += "</b>"
                } else {
                    ret += "<b>"
                }
            }
            prev = buckets[i]
            ret += s[i]
            i++
        }
        // add tail tag
        if (prev) {
            ret += "</b>"
        }
        return ret
    }
}

fun main(args: Array<String>) {
    val s = Solution_add_bold_tag_in_string()
    println(s.addTags("abcxyz123", arrayOf("abc", "123")))
    println(s.addTags("aaabbcc", arrayOf("aaa", "aab", "bc")))

    println(s.addTags2("abcxyz123", arrayOf("abc", "123")))
    println(s.addTags2("aaabbcc", arrayOf("aaa", "aab", "bc")))
}