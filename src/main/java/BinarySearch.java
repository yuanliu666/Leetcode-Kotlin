public class BinarySearch {

    // assume arr is already ascending sorted, no duplicate, and target is always valid
    // T:O(logn) S:O(1)
    public int findExactIndex(int[] arr, int t) {
        int ret = -1;
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int m = (lo + hi) / 2;
            if (arr[m] < t) {
                lo = m + 1;
            } else if (arr[m] > t) {
                hi = m - 1;
            } else {
                ret = m;
                break;
            }
        }
        return ret;
    }

    // recur version
    public int findExactIndexRecur(int[] arr, int target) {
        return findExactIndexRecurHelper(arr, target, 0, arr.length - 1);
    }

    private int findExactIndexRecurHelper(int[] arr, int t, int lo, int hi) {
        if (lo > hi) return -1;
        int m = (lo + hi) / 2;
        if (arr[m] < t) {
            return findExactIndexRecurHelper(arr, t, m + 1, hi);
        } else if (arr[m] > t) {
            return findExactIndexRecurHelper(arr, t, lo, m - 1);
        } else {
            return m;
        }
    }

    // find the index that has closest value to target
    // assume arr is already ascending sorted, no duplicate, and only one valid result
    // T:O(logn) S:O(1)
    public int findClosestIndex(int[] arr, int t) {
        int lo = 0;
        int hi = arr.length - 1;
        int m;
        while (lo + 1 < hi) {
            m = (lo + hi) / 2;
            if (arr[m] == t) {
                return m;
            } else if (m - 1 >= 0
                    && arr[m - 1] <= t
                    && t < arr[m]) {
                return getClosestIndex(arr, m - 1, m, t);
            } else if (m + 1 < arr.length
                    && arr[m] < t
                    && t <= arr[m + 1]) {
                return getClosestIndex(arr, m, m + 1, t);
            } else if (arr[m] < t) {
                lo = m;
            } else {
                hi = m;
            }
        }
        return getClosestIndex(arr, lo, hi, t);
    }

    // assumes idx2 >= idx1 and arr ascending sorted
    private int getClosestIndex(int[] arr, int idx1, int idx2, int t) {
        if (t - arr[idx1] >= arr[idx2] - t) {
            return idx2;
        } else {
            return idx1;
        }
    }
}
