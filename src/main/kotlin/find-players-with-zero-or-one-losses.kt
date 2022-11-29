/**
 * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
 * Return a list answer of size 2 where:
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 * Note:
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 */
class Solution_find_players_with_zero_or_one_losses {

    // T:O(nlogn) S:O(n)
    fun findWinners(matches: Array<IntArray>): List<List<Int>> {
        // Map of player - losses
        val map = hashMapOf<Int, Int>()
        for (m in matches) {
            val winner = m[0]
            val loser = m[1]
            map.putIfAbsent(loser, 0)
            map[loser] = map[loser]!! + 1
            // register winner as well
            map.putIfAbsent(winner, 0)
        }
        val zeroLost = mutableListOf<Int>()
        val oneLost = mutableListOf<Int>()
        for (n in map.keys) {
            if (map[n] == 0) {
                zeroLost.add(n)
            }
            if (map[n] == 1) {
                oneLost.add(n)
            }
        }
        return listOf(zeroLost.sorted(), oneLost.sorted())
    }
}