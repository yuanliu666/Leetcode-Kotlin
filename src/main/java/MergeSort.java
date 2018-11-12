import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    // T:O(nlogn) S:O(n)
    public int[] mergeSort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        helper(a, 0, a.length);
        return a;
    }

    private void helper(int[] arr, int s, int e) {
        if (e - s <= 1) return;
        int m = s + ((e - s) >> 1);
        helper(arr, s, m);
        helper(arr, m, e);
        int r = m;
        List<Integer> tmp = new ArrayList<>();
        for (int i = s; i < m; i++) {
            while (r < e && arr[r] < arr[i]) {
                tmp.add(arr[r]);
                r++;
            }
            tmp.add(arr[i]);
        }
        for (int i = 0; i < tmp.size(); i++) {
            arr[s + i] = tmp.get(i);
        }
    }
}
