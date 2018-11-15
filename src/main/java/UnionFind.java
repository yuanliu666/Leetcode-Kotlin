// union find without rank, but with count
public class UnionFind {

    // p stands for parents
    private int[] p;
    private int cnt;

    // T:O(n) S:O(n)
    UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        cnt = n;
    }

    // T:O(logn) S:O(1)
    public int find(int i) {
        while (p[i] != i) {
            p[i] = p[p[i]];
            i = p[i];
        }
        return i;
    }

    // T:O(logn) S:O(1)
    public void union(int i, int j) {
        int a = find(i);
        int b = find(j);
        if (a != b) {
            p[a] = b;
            cnt--;
        }
    }

    public int getCount() {
        return cnt;
    }
}
