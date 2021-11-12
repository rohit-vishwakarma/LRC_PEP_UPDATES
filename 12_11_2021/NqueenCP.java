public class NqueenCP {
    public static boolean isSafeToPlaceQueen(boolean[][] chess, int row, int col) {
        // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
        int n = chess.length, m = chess[0].length;
        for (int[] d : dir) {
            int r = row + d[0];
            int c = col + d[1];
            while (r >= 0 && c >= 0 && r < n && c < m) {
                if (!chess[r][c])
                    return false;

                r += d[0];
                c += d[1];
            }
        }
        return true;
    }

    public static int nqueenCombination(boolean[][] chess, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = chess.length, m = chess[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (isSafeToPlaceQueen(chess, r, c)) {
                chess[r][c] = true;
                count += nqueenCombination(chess, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                chess[r][c] = false;
            }
        }

        return count;
    }

    public static int nqueenPermutation(boolean[][] chess, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int n = chess.length, m = chess[0].length, count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!chess[r][c] && isSafeToPlaceQueen(chess, r, c)) {
                chess[r][c] = true;
                count += nqueenPermutation(chess, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                chess[r][c] = false;
            }
        }

        return count;
    }

    static boolean[] rows;
    static boolean[] cols;
    static boolean[] diag;
    static boolean[] adiag;

    static int calls = 0;

    public static int nqueenCombination2(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        calls++;
        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueenCombination2(n, m, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int nqueenPermutation2(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < n * m; i++) {
            int r = i / m;
            int c = i % m;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += nqueenPermutation2(n, m, tnq - 1, 0, ans + "(" + r + ", " + c + ") ");
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int nqueenCombinationOpti(int floor, int tnq, int m, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return 1;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += nqueenCombinationOpti(floor + 1, tnq - 1, m, ans + "(" + r + ", " + c + ") ");

                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        return count;
    }

    public static int nqueenPermutationOpti(int floor, int tnq, int m, int n, String ans) {
        if (tnq == 0 || floor == n) {
            if (tnq == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        calls++;
        int count = 0;
        for (int room = 0; room < m; room++) {
            int r = floor, c = room;
            if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;

                count += nqueenPermutationOpti(0, tnq - 1, m, n, ans + "(" + r + ", " + c + ") ");

                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }

        count += nqueenPermutationOpti(floor + 1, tnq, m, n, ans);

        return count;
    }

    public static void Nqueen() {
        int n = 4, q = 4;
        // boolean[][] chess = new boolean[n][n];
        // System.out.println(nqueenCombination(chess, q, 0, ""));
        // System.out.println(nqueenPermutation(chess, q, 0, ""));

        rows = new boolean[n];
        cols = new boolean[n];
        diag = new boolean[n + n - 1];
        adiag = new boolean[n + n - 1];

        System.out.println(nqueenCombination2(n, n, q, 0, ""));
        System.out.println(nqueenPermutationOpti(0, q, n,n, ""));
        System.out.println(calls);
    }

    public static void main(String[] args) {
        Nqueen();
    }

}
