public class Nqueens2 {
    
    boolean[] rows;
    boolean[] cols;
    boolean[] diag;
    boolean[] adiag;

   public int nqueenCombination2(int n, int m, int tnq, int idx) {
       if (tnq == 0) {
           return 1;
       }
       int count = 0;
       for (int i = idx; i < n * m; i++) {
           int r = i / m;
           int c = i % m;
           if (!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]) {
               rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
               count += nqueenCombination2(n, m, tnq - 1, i + 1);
               rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
           }
       }

       return count;
   }

   public int totalNQueens(int n) {
       
        rows = new boolean[n];
       cols = new boolean[n];
       diag = new boolean[n + n - 1];
       adiag = new boolean[n + n - 1];
       
       return nqueenCombination2(n,n,n,0);
   }
}
