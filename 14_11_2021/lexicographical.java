
import java.io.*;
import java.util.*;
public class lexicographical {
    public static void dfs(int i, int n){
        System.out.println(i);
        for(int j= 0; j<=9; j++){
            if(i*10 +j <=n)
                dfs(i*10 + j, n);
        }
    }
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();

		//write your code here
		for(int i =1; i<=9; i++){
		    dfs(i, n);
		}
	}
	
}
