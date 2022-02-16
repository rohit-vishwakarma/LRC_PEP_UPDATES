// import java.io.*;
import java.util.*;

public class Sudoku {
  public static void display(int[][] board){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public static boolean isValid(int[][] arr,int r, int c, int val){
      int n = arr.length;
      for(int i=0; i<n; i++){
          if(arr[r][i] == val) return false;
      }
      for(int i=0; i<n; i++){
          if(arr[i][c] == val) return false;
      }
      r = (r/3)*3;
      c = (c/3)*3;
      for(int i=0; i<3; i++)
      for(int j=0; j<3;j++){
          if(arr[r+i][c+j] == val) return false;
      }
      return true;
  }

  public static void solveSudoku(int[][] board, int i, int j) {
    // write yopur code here
    if(i == board.length){
        display(board);
        return;
    }
    int r = 0;
    int c= 0;
    
    if(j == board[0].length-1){
         r = i+1;
         c = 0;
    }else{
        r = i;
        c = j+1;
    }
    if(board[i][j] !=0){
        solveSudoku(board, r, c);
    }else{
        for(int val = 1; val<=9; val++){
            if(isValid(board, i, j, val)){
                board[i][j] = val;
                solveSudoku(board, r,c);
                board[i][j] = 0;
            }
        }
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int[][] arr = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        arr[i][j] = scn.nextInt();
      }
    }

    solveSudoku(arr, 0, 0);
  }
}
