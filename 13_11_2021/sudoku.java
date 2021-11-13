public class sudoku {
    public boolean[][] rows;
    public boolean[][] cols;
    public boolean[][][] mat;
    
     public boolean sudokuSolver(char[][] board,ArrayList<Integer> cells, int idx) {
        if (idx == cells.size()) {
            return true;
        }

        int r = cells.get(idx) / 9;
        int c = cells.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (!rows[r][num] && !cols[c][num] && !mat[r/3][c/3][num]) {
                rows[r][num] = cols[c][num] = mat[r/3][c/3][num] = true;
                board[r][c] = (char) (num + '0');
                if (sudokuSolver(board,cells, idx + 1))
                    return true;
                
                rows[r][num] = cols[c][num] = mat[r/3][c/3][num] = false;
                board[r][c] = '.';
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        rows = new boolean[10][10];
        cols = new boolean[10][10];
        mat = new boolean[3][3][10];
        
        ArrayList<Integer> cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    cells.add(i * 9 + j);
                }else{
                    int num = board[i][j] - '0';
                    rows[i][num] = cols[j][num] = mat[i/3][j/3][num] = true;
                }

            }
        }
        sudokuSolver(board,cells, 0);    
    }
}
