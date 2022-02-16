public class Queens {

    public static int queenCombination(boolean[] board, int idx, int tnq, int qpsf, String asf)
    {
        if (qpsf == tnq)
        {
            System.out.println(asf);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < board.length; i++)
        {
            count += queenCombination(board, i + 1, tnq, qpsf + 1, asf + "P" + i+ "Q" + qpsf + " ");
        }

        return count;
    }

    public static int queenPermutation(boolean[] board, int idx, int tnq, int qpsf, String asf)
    {
        if (qpsf == tnq)
        {
            System.out.println(asf); 
            return 1;
        }

        int count = 0;
        for (int i = idx; i < board.length; i++)
        {
            if (!board[i])
            {
                board[i] = !board[i];
                count += queenPermutation(board, 0, tnq, qpsf + 1, asf + "P" + i + "Q" + qpsf + " ");
                board[i] = !board[i];
            }
        }

        return count;
    }

    public static void oneDQueen()
    {
        int n = 5, tq = 3;
        boolean[] board = new boolean[n];
        System.out.println(queenCombination(board, 0, tq, 0, ""));
        System.out.println(queenPermutation(board, 0, tq,0, ""));

    }

    public static int queenCombinationSS(boolean[] board, int idx, int tnq, int qpsf, String asf)
    {
        if (idx == board.length || qpsf == tnq)
        {
            if(qpsf == tnq){
            System.out.println(asf);
            return 1;
            }
            return 0;
        }

        int count = 0;
        
        count += queenCombinationSS(board, idx + 1, tnq, qpsf + 1, asf + "P" + idx+ "Q" + qpsf + " ");
        count += queenCombinationSS(board, idx + 1, tnq, qpsf + 1, asf );
        

        return count;
    }

    public static int queenPermutationSS(boolean[] board, int idx, int tnq, int qpsf, String asf)
    {
        if (qpsf == tnq)
        {
            System.out.println(asf); 
            return 1;
        }

        int count = 0;
        
        if (!board[idx])
        {
            board[idx] = !board[idx];
            count += queenPermutationSS(board, 0, tnq, qpsf + 1, asf + "P" + idx + "Q" + qpsf + " ");
            board[idx] = !board[idx];
        }
        count += queenPermutationSS(board, idx + 1, tnq, qpsf + 1, asf );
        

        return count;
    }

// =================================== 2D ===============================
    public static int queenCombination2D(boolean[][] board, int idx, int tnq, String asf)
    {
        if (tnq == 0)
        {
            System.out.println(asf); 
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = idx; i < n * m; i++)
        {
            int r = i / m, c = i % m;
            count += queenCombination2D(board, i + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        }

        return count;
    }

    public static int queenPermutation2D(boolean[][] board, int idx, int tnq, String asf)
    {
        if (tnq == 0)
        {
            System.out.println(asf); 
            return 1;
        }

        int count = 0, n = board.length, m = board[0].length;
        for (int i = idx; i < n * m; i++)
        {
            int r = i / m, c = i % m;
            if (!board[r][c])
            {
                board[r][c] = !board[r][c];
                count += queenPermutation2D(board, 0, tnq - 1,asf + "(" + r + "," + c + ") ");
                board[r][c] = !board[r][c];
            }
        }

        return count;
    }

    public static void TwoDQueen()
    {
        int n = 3, tq = 3;

        boolean[][] board = new boolean[n][n];

        // System.out.println(queenPermutation2D(board, 0, tq, ""));
        // System.out.println(queenCombination2D(board, 0, tq, ""));
        System.out.println(queenPermutation2DSS(board, 0, tq, ""));
        System.out.println(queenCombination2DSS(board, 0, tq, ""));
    }

    public static int queenCombination2DSS(boolean[][] board, int idx, int tnq, String asf)
    {
        if(tnq == 0 || idx == board.length*board[0].length){
            if (tnq == 0)
            {
            System.out.println(asf); 
            return 1;
            }
        }


        int count = 0,  m = board[0].length;
        
        int r = idx / m, c = idx % m;
        count += queenCombination2DSS(board, idx + 1, tnq - 1, asf + "(" + r + "," + c + ") ");
        count += queenCombination2DSS(board, idx + 1, tnq - 1, asf );
        

        return count;
    }

    public static int queenPermutation2DSS(boolean[][] board, int idx, int tnq, String asf)
    {
        if(tnq == 0 || idx == board.length*board[0].length){
            if (tnq == 0)
            {
                System.out.println(asf); 
                return 1;
                }
            }
    
    
            int count = 0,  m = board[0].length;
            
            int r = idx / m, c = idx % m;
            if(!board[r][c]){
                board[r][c] = !board[r][c];
                count += queenCombination2DSS(board, 0, tnq - 1, asf + "(" + r + "," + c + ") ");
                board[r][c] = !board[r][c];
                }
            count += queenCombination2DSS(board, idx + 1, tnq, asf );
            
            return count;
    }

    public static void main(String[] args) {
        oneDQueen();
        TwoDQueen();
    }
}
