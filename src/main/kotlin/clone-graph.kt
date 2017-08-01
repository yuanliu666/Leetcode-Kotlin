/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *
 *
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 *
 * We use * as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2*1,2*2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as separated by *.
 *
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 */

import utils.UndirectedGraphNode
import utils.myDeque

class Solution_clone_graph {
    // T:O(n) S:O(n)
    fun cloneGraph(n: UndirectedGraphNode): UndirectedGraphNode {
        val copy = UndirectedGraphNode(n.label)
        val map = mutableMapOf(n to copy)
        val q = myDeque<UndirectedGraphNode>()
        while (q.peekFirst() != null) {
            val node = q.getFirst()
            node?.neighbors?.forEach {
                if (!map.containsKey(it)) {
                    q.addLast(it)
                    val temp = UndirectedGraphNode(it.label)
                    map.put(it, temp)
                }
                map[node]?.neighbors?.add(map[it]!!)
            }
        }
        return copy
    }
}

fun main(args: Array<String>) {

}