public class l002 {

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

    void oneDQueen()
    {
        int n = 5, tq = 3;
        boolean[] board = new boolean[n];

        
    }

        // 2d Queens ===================================================

    int queenCombination2D(vector<vector<bool>> &board, int idx, int tnq, string asf)
    {
        if (tnq == 0)
        {
            cout << asf << endl;
            return 1;
        }

        int count = 0, n = board.size(), m = board[0].size();
        for (int i = idx; i < n * m; i++)
        {
            int r = i / m, c = i % m;
            count += queenCombination2D(board, i + 1, tnq - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
        }

        return count;
    }

    int queenPermutation2D(vector<vector<bool>> &board, int idx, int tnq, string asf)
    {
        if (tnq == 0)
        {
            cout << asf << endl;
            return 1;
        }

        int count = 0, n = board.size(), m = board[0].size();
        for (int i = idx; i < n * m; i++)
        {
            int r = i / m, c = i % m;
            if (!board[r][c])
            {
                board[r][c] = !board[r][c];
                count += queenPermutation2D(board, 0, tnq - 1, asf + "(" + to_string(r) + "," + to_string(c) + ") ");
                board[r][c] = !board[r][c];
            }
        }

        return count;
    }

    void twoDQueen()
    {
        int n = 4, tnq = 4;

        vector<vector<bool>> board(n, vector<bool>(n, false));

        // cout << queenCombination2D(board, 0, tnq, "") << endl;
        cout << queenPermutation2D(board, 0, tnq, "") << endl;
    }

    public static void main(String[] args) {

    }
}
