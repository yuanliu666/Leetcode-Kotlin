/**
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas
 * to travel from the ith station to its next (i + 1)th station.
 * You begin the journey with an empty tank at one of the gas stations.
 * Given two integer arrays gas and cost, return the starting gas station's index
 * if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * If there exists a solution, it is guaranteed to be unique
 */
class Solution_gas_station {

    // The idea is, consider A -> .. -> B, if at B the tank is first time negative, meaning A cannot reach B,
    // and the stations between them also cannot reach B. Why?
    // Because assume there is a station C in between, when A arrives at C, the tank is not negative,
    // then C can get a buff comparing to starting with 0 gas, so it cannot do better than A.
    // Hence, choose next start point and try again.
    // T:O(n) S:O(1)
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n = gas.size
        var totalTank = 0
        var currTank = 0
        var startingStation = 0
        for (i in 0 until n) {
            totalTank += gas[i] - cost[i]
            currTank += gas[i] - cost[i]
            // If one couldn't get here,
            if (currTank < 0) {
                // Pick up the next station as the starting one.
                startingStation = i + 1
                // Start with an empty tank.
                currTank = 0
            }
        }
        return if (totalTank >= 0) startingStation else -1
    }
}