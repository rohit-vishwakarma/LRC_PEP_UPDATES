import java.util.ArrayList;
public class Sudoku {
    public boolean isSafeToPlaceNumber(char[][] board, int row, int col, int num) {

        int n = board.length, m = board[0].length;
        // row checking
        for (int j = 0; j < m; j++)
            if ((board[row][j] - '0') == num)
                return false;

        // col checking
        for (int i = 0; i < n; i++)
            if ((board[i][col] - '0') == num)
                return false;

        // matrix checking
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if ((board[i + row][j + col] - '0') == num){
                    return false;
                }
            }
        }
        return true;
    }
    
     public boolean sudokuSolver(char[][] board, ArrayList<Integer> cells, int idx) {
        if (idx == cells.size()) {
            return true;
        }

        int r = cells.get(idx) / 9;
        int c = cells.get(idx) % 9;

        for (int num = 1; num <= 9; num++) {
            if (isSafeToPlaceNumber(board, r, c, num)) {
                board[r][c] = (char) (num + '0');
                if (sudokuSolver(board, cells, idx + 1))
                    return true;
                board[r][c] = '.';
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<Integer> cells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    cells.add(i * 9 + j);
                }
            }
        }
        sudokuSolver(board,cells, 0);    
    }
}
