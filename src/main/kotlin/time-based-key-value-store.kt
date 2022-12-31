/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 * and retrieve the key's value at a certain timestamp.
 * Implement the TimeMap class:
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value
 * at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously,
 * with timestamp_prev <= timestamp. If there are multiple such values,
 * it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 */
class Solution_time_based_key_value_store {

    // S:O(n)
    private val map = hashMapOf<String, MutableList<Pair<String, Int>>>()

    // T:O(L) where L is length of string
    fun set(key: String, value: String, timestamp: Int) {
        map.putIfAbsent(key, mutableListOf())
        map[key]?.add(Pair(value, timestamp))
    }

    // T:O(L + logN) using binary search on timestamp
    fun get(key: String, timestamp: Int): String {
        if (!map.containsKey(key)) return ""
        val list = map[key]!!
        if (timestamp < list[0].second) return ""
        if (timestamp == list[0].second) return list[0].first
        if (timestamp >= list[list.size - 1].second) return list[list.size - 1].first

        var lo = 0
        var hi = list.size
        while (lo < hi) {
            val m = (hi - lo) / 2 + lo
            val time = list[m].second
            when {
                timestamp == time -> return list[m].first
                timestamp > time -> lo = m
                else -> hi = m - 1
            }
        }
        return list[lo].first
    }
}