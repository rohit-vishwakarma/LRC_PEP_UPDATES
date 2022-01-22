import java.util.*;

public class leetcode {

    // 338.
    public static int countBits(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = n;
        }
        countBits(n - 1, dp);
        int a = (n % 2 == 1 ? 1 : 0) + dp[n / 2];
        return dp[n] = a;

    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        countBits(n, ans);
        return ans;
    }

    // 1025. Divisor Game
    public boolean divisorGame(int n) {
        if (n == 1)
            return false;
        int[] dp = new int[n + 1];
        for (int x = 2; x <= n; x++) {
            for (int i = 1; i * i <= x; i++) {
                if (x % i == 0 && dp[x - i] == 0) {
                    dp[x] = 1;
                }
            }
        }
        // for(int ele : dp){
        // System.out.println(ele+" ");
        // }
        return dp[n] == 1;
    }
//1137. N-th Tribonacci Number
    public int tribonacci(int n) {
        if(n==0)
            return 0;
        if(n==1 || n==2)
            return 1;
        int[] T = new int[n+1];
        T[0]=0;
        T[1]=1;
        T[2]=1;
        for(int i=3;i<=n;i++)
        {
            T[i]=T[i-3]+T[i-2]+T[i-1];
        }
        return T[n];
            
    }
}
