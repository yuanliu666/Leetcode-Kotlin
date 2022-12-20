/**
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0.
 * Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 * When you visit a room, you may find a set of distinct keys in it.
 * Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i,
 * return true if you can visit all the rooms, or false otherwise.
 */
class Solution_keys_and_rooms {

    // Cannot directly use union find since connection in union find is bi-directional, but here a locked room can
    // have key for unlocked room which is hard to handle. Simple BFS is enough.
    // T:O(n+m), where n is # of rooms and m is # of keys; S:O(n)
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        if (rooms.size <= 1) return true
        val n = rooms.size
        var cnt = 1
        val visited = BooleanArray(n)
        visited[0] = true
        val q = LinkedList<Int>()
        q.addAll(rooms[0])
        while (q.isNotEmpty()) {
            val room = q.poll()
            if (!visited[room]) {
                visited[room] = true
                cnt++
                q.addAll(rooms[room])
            }
            if (cnt == n) return true
        }
        return false
    }
}