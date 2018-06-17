/**
 * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
 *
 * The world is modeled as a 2-D array of cells, where 0 represents uninfected cells,
 * and 1 represents cells contaminated with the virus.
 * A wall (and only one wall) can be installed between any two 4-directionally adjacent cells,
 * on the shared boundary.
 *
 * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall.
 * Resources are limited. Each day, you can install walls around only one region
 * -- the affected area (continuous block of infected cells)
 * that threatens the most uninfected cells the following night.
 * There will never be a tie.
 *
 * Can you save the day? If so, what is the number of walls required?
 * If not, and the world becomes fully infected, return the number of walls used.
 *
 * Example 1:
 * Input: grid =
 * [[0,1,0,0,0,0,0,1],
 * [0,1,0,0,0,0,0,1],
 * [0,0,0,0,0,0,0,1],
 * [0,0,0,0,0,0,0,0]]
 * Output: 10
 * Explanation:
 * There are 2 contaminated regions.
 * On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
 *
 * [[0,1,0,0,0,0,1,1],
 * [0,1,0,0,0,0,1,1],
 * [0,0,0,0,0,0,1,1],
 * [0,0,0,0,0,0,0,1]]
 *
 * On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
 *
 * Example 2:
 * Input: grid =
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * Output: 4
 * Explanation: Even though there is only one cell saved, there are 4 walls built.
 * Notice that walls are only built on the shared boundary of two different cells.
 *
 * Example 3:
 * Input: grid =
 * [[1,1,1,0,0,0,0,0,0],
 * [1,0,1,0,1,1,1,1,1],
 * [1,1,1,0,0,0,0,0,0]]
 * Output: 13
 * Explanation: The region on the left only builds two new walls.
 *
 * Note:
 * 1. The number of rows and columns of grid will each be in the range [1, 50].
 * 2. Each grid\[i]\[j] will be either 0 or 1.
 * 3. Throughout the described process,
 * there is always a contiguous viral region
 * that will infect strictly more uncontaminated squares in the next round.
 */

class Solution_contain_virus {

    private val directions = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

    // reference for complexity: https://leetcode.com/problems/contain-virus/solution/
    // T:  O((m * n)^(4/3)), days = O((m * n)^(1/3))
    // S: O(m * n)
    fun containVirus(grid: Array<IntArray>): Int {
        var ret = 0
        while (true) {
            // record seen cells
            val lookup = mutableSetOf<Pair<Int, Int>>()
            // record infected cells
            val regions = mutableListOf<MutableSet<Pair<Int, Int>>>()
            // record cells about to be infected
            val frontiers = mutableListOf<MutableSet<Pair<Int, Int>>>()
            // record number of walls required to lock down
            val perimeters = mutableListOf<Int>()
            for ((r, row) in grid.withIndex()) {
                for ((c, value) in row.withIndex()) {
                    if (value == 1 && !lookup.contains(Pair(r, c))) {
                        regions.add(mutableSetOf())
                        frontiers.add(mutableSetOf())
                        perimeters.add(0)
                        dfs(grid, r, c, lookup, regions, frontiers, perimeters)
                    }
                }
            }
            if (regions.isEmpty()) {
                break
            }
            val triageIdx = frontiers.indexOf(frontiers.maxBy { it.size })
            for ((i, region) in regions.withIndex()) {
                // lock down most threatening region
                if (i == triageIdx) {
                    ret += perimeters[i]
                    for ((r, c) in region) {
                        // set locked down region to -1 and forget about it
                        grid[r][c] = -1
                    }
                    continue
                }
                // expand other infected regions
                for ((r, c) in region) {
                    for (d in directions) {
                        val nr = r + d.first
                        val nc = c + d.second
                        if (!(0 <= nr && nr < grid.size && 0 <= nc && nc < grid[r].size)) {
                            continue
                        }
                        if (grid[nr][nc] == 0) {
                            grid[nr][nc] = 1
                        }
                    }
                }
            }
        }
        return ret
    }

    private fun dfs(grid: Array<IntArray>,
                    r: Int,
                    c: Int,
                    lookup: MutableSet<Pair<Int, Int>>,
                    regions: MutableList<MutableSet<Pair<Int, Int>>>,
                    frontiers: MutableList<MutableSet<Pair<Int, Int>>>,
                    perimeters: MutableList<Int>) {
        if (lookup.contains(Pair(r, c))) {
            return
        }
        lookup.add(Pair(r, c))
        regions.last().add(Pair(r, c))
        for (d in directions) {
            val nr = r + d.first
            val nc = c + d.second
            if (!(0 <= nr && nr < grid.size && 0 <= nc && nc < grid[r].size)) {
                continue
            }
            if (grid[nr][nc] == 1) {
                dfs(grid, nr, nc, lookup, regions, frontiers, perimeters)
            } else if (grid[nr][nc] == 0) {
                frontiers.last().add(Pair(nr, nc))
                perimeters[perimeters.lastIndex] += 1
            }
        }
    }
}

fun main(args: Array<String>) {
    // LC OJ Passed
    // See [ContainVirusTest] for unit test
}