import java.io.*;
import java.util.*;

public class CrossWord {

  public static boolean canPlaceVert(char[][] arr, String word, int r, int c){
      int i=0;
      for(; i<word.length(); i++){
          if(r+i >= arr.length) return false;
          if(arr[r+i][c] == '-' || arr[r+i][c] == word.charAt(i)){
              continue;
          }else return false;
      }
      if( r+ i == arr.length || arr[r+i][c] == '+'){
          return true;
      }
      return false;
  }
  public static boolean canPlaceHort(char[][] arr, String word, int r, int c){
      int i=0;
      for(; i<word.length(); i++){
          if(c+i >= arr[0].length) return false;
          if(arr[r][c+i] == '-' || arr[r][c+i] == word.charAt(i)){
              continue;
          }else return false;
      }
      if(c+ i == arr[0].length || arr[r][c+i] == '+'){
          return true;
      }
      return false;
  }
  public static void placeVer(char[][] arr, String word, int r, int c, boolean[] isPlaced){
      for(int i=0; i<word.length(); i++){
          if(arr[r+i][c] == '-') isPlaced[i] =  true;
          arr[r+i][c] = word.charAt(i);
      }
  }
  public static void placeHor(char[][] arr, String word, int r, int c, boolean[] isPlaced){
      for(int i=0; i<word.length(); i++){
          if(arr[r][c+i] == '-') isPlaced[i] = true;
          arr[r][c+i] = word.charAt(i);
      }
  }
  public static void unplaceVer(char[][] arr, String word, int r, int c, boolean[] isPlaced){
      for(int i=0; i<word.length(); i++){
            if(isPlaced[i]) arr[r+i][c] = '-';
      }
  }
  public static void unplaceHor(char[][] arr, String word, int r, int c, boolean[] isPlaced){
      for(int i=0; i<word.length(); i++){
            if(isPlaced[i]) arr[r][c+i] = '-';
      }
  }
  public static void solution(char[][] arr, String[] words, int vidx) {
    //write your code here
    if(vidx == words.length){
        print(arr);
        return;
    }
    
    String str = words[vidx];
    for(int i=0; i<arr.length; i++){
        for(int j=0; j<arr[0].length; j++){
            if(arr[i][j] == '-' || arr[i][j] == str.charAt(0)){
                if(canPlaceVert(arr, str, i ,j)){
                    boolean[] isplaced = new boolean[str.length()];
                    placeVer(arr, str, i, j, isplaced);
                    solution(arr, words, vidx + 1);
                    unplaceVer(arr, str, i, j, isplaced);
                }
                if(canPlaceHort(arr, str, i ,j)){
                    boolean[] isplaced = new boolean[str.length()];
                    placeHor(arr, str, i, j, isplaced);
                    solution(arr, words, vidx + 1);
                    unplaceHor(arr, str, i, j, isplaced);
                }
            }
        }
    }

  }


  public static void print(char[][] arr) {
    for (int i = 0 ; i < arr.length; i++) {
      for (int j = 0 ; j < arr.length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }

  }
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    char[][] arr = new char[10][10];
    for (int i = 0 ; i < arr.length; i++) {
      String str = scn.next();
      arr[i] = str.toCharArray();
    }
    int n = scn.nextInt();
    String[] words = new String[n];
    for (int i = 0 ; i  < words.length; i++) {
      words[i] = scn.next();
    }
    solution(arr, words, 0);
  }
}