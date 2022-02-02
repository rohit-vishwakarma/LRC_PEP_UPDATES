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

    // 1137. N-th Tribonacci Number
    public int tribonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int[] T = new int[n + 1];
        T[0] = 0;
        T[1] = 1;
        T[2] = 1;
        for (int i = 3; i <= n; i++) {
            T[i] = T[i - 3] + T[i - 2] + T[i - 1];
        }
        return T[n];
    }

    // GoldMine
    static int maxGold(int n, int m, int mat[][]) {
        // code here
        int[][] dir = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
        int[][] dp = new int[n][m];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        int ans = 0;
        for (int r = 0; r < n; r++) {
            ans = Math.max(ans, helper(r, 0, mat, dp, dir));
        }
        return ans;
    }

    static int helper(int sr, int sc, int[][] mat, int[][] dp, int[][] dir) {
        if (sc == mat[0].length - 1) {
            return dp[sr][sc] = mat[sr][sc];
        }
        if (dp[sr][sc] != -1)
            return dp[sr][sc];
        int max = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];
            if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length) {
                max = Math.max(max, helper(r, c, mat, dp, dir) + mat[sr][sc]);
            }
        }
        return dp[sr][sc] = max;
    }

    // 70 Climbing stairs
    public int climbStairs(int n) {

        int a = 1, b = 1;
        for (int i = 1; i <= n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
        // int[] dp = new int[n+1];
        // return climbStairs_memo(n, dp);
    }

    public int climbStairs_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int a = climbStairs_memo(n - 1, dp);
        int b = climbStairs_memo(n - 2, dp);
        return dp[n] = a + b;
    }

    public int climbStairs_tabu(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int a = dp[n - 1];
            int b = dp[n - 2];
            dp[n] = a + b;
        }
        return dp[N];
    }

    // 746
    public int helper(int[] cost, int idx, int[] dp) {
        if (idx <= 1) {
            return dp[idx] = cost[idx];
        }
        if (dp[idx] != 0)
            return dp[idx];
        int a = helper(cost, idx - 1, dp);
        int b = helper(cost, idx - 2, dp);
        return dp[idx] = Math.min(a, b) + (idx == cost.length ? 0 : cost[idx]);
    }

    public int minCostClimbingStairs(int[] cost) {
        return helper(cost, cost.length, new int[cost.length + 1]);
    }

    // 91 decode ways
    public int numDecodings(String s) {
        return helper(s, 0, new int[s.length() + 1]);
    }

    public int helper(String s, int idx, int[] dp) {
        if (idx == s.length()) {
            return dp[idx] = 1;
        }
        if (dp[idx] != 0)
            return dp[idx];
        if (s.charAt(idx) == '0')
            return 0;
        int c = helper(s, idx + 1, dp);
        if (idx + 1 < s.length()) {
            int v = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (v <= 26) {
                c += helper(s, idx + 2, dp);
            }
        }
        return dp[idx] = c;
    }

    // 639 decode ways 2
    public long helper(String s, int idx, long[] dp) {
        if (s.length() == idx) {
            return dp[idx] = 1;
        }
        if (dp[idx] != -1)
            return dp[idx];
        if (s.charAt(idx) == '0')
            return dp[idx] = 0;
        long count = 0, mod = (long) 1e9 + 7;
        char ch = s.charAt(idx);
        if (ch == '*') {
            count = (count + (9 * helper(s, idx + 1, dp)) % mod) % mod; // fc = * than 9 ways
            if (idx + 1 < s.length()) {
                if (s.charAt(idx + 1) == '*') { // sc = * than 15 ways
                    count = (count + (15 * helper(s, idx + 2, dp)) % mod) % mod;
                } else if (s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6') {
                    count = (count + (2 * helper(s, idx + 2, dp)) % mod) % mod;
                } else {
                    count = (count + helper(s, idx + 2, dp) % mod) % mod;
                }
            }
        } else {
            count = (count + helper(s, idx + 1, dp) % mod) % mod;
            if (idx + 1 < s.length()) {
                if (s.charAt(idx + 1) == '*' && ch == '1') {
                    count = (count + (9 * helper(s, idx + 2, dp)) % mod) % mod;
                } else if (s.charAt(idx + 1) == '*' && ch == '2') {
                    count = (count + (6 * helper(s, idx + 2, dp)) % mod) % mod;
                } else if (s.charAt(idx + 1) != '*') {
                    int val = (ch - '0') * 10 + (s.charAt(idx + 1) - '0');
                    if (val <= 26) {
                        count = (count + helper(s, idx + 2, dp) % mod) % mod;
                    }
                }
            }
        }
        return dp[idx] = count;
    }

    public int numDecodings2(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        return (int) helper(s, 0, dp);
    }

    // Friends pairing problem gfg
    public long helper(int n, long[] dp) {
        if (n <= 1)
            return dp[n] = 1;

        if (dp[n] != -1)
            return dp[n];
        long mod = (long) 1e9 + 7;
        long single = helper(n - 1, dp) % mod;
        long pair = (helper(n - 2, dp) * (n - 1)) % mod;

        return dp[n] = (single + pair) % mod;
    }

    public long helper(int N) {
        long[] dp = new long[N + 1];
        long mod = (long) 1e9 + 7;
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            long single = dp[n - 1] % mod;
            long pair = (dp[n - 2] * (n - 1)) % mod;

            dp[n] = (single + pair) % mod;
        }
        return dp[N];
    }

    public long countFriendsPairings(int n) {
        // code here
        // long[] dp = new long[n+1];
        // Arrays.fill(dp, -1);
        return helper(n);
    }

    // K partitions Pepcoding
    public static long ways(int n, int k) {
        // write your code here
        if (n == k)
            return 1;
        if (n < k || k == 0)
            return 0;
        long ans = 0;
        ans = k * ways(n - 1, k) + ways(n - 1, k - 1);

        return ans;

    }

    public static long ways(int n, int k, long[][] dp) {
        if (n == k)
            return 1;
        if (n < k || k == 0)
            return 0;
        if (dp[n][k] != 0)
            return dp[n][k];
        long ans = 0;
        ans = k * ways(n - 1, k, dp) + ways(n - 1, k - 1, dp);

        return dp[n][k] = ans;
    }

    public static long Ways(int N, int K) {
        long[][] dp = new long[N + 1][K + 1];
        for (int n = 0; n <= N; n++) {
            for (int k = 0; k <= K; k++) {
                if (n == k) {
                    dp[n][k] = 1;
                } else if (n < k) {
                    dp[n][k] = 0;
                } else if (k == 0) {
                    dp[n][k] = 0;
                } else {
                    dp[n][k] = dp[n - 1][k] * k + dp[n - 1][k - 1];
                }
            }
        }
        return dp[N][K];
    }

    //1458 maxDotProduct L-4
    public int helper1(int[]a, int[] b, int n, int m, int[][] dp){
        
        if(n < 0 || m < 0) return -(int)1e9;
        
        if(dp[n][m] != -1) return dp[n][m];
        
        dp[n][m] = a[n] * b[m];
        
        dp[n][m] += Math.max(helper1(a,b, n-1, m-1,dp), 0);
        
        dp[n][m] = Math.max(helper1(a,b,n-1,m, dp), dp[n][m]);
        dp[n][m] = Math.max(helper1(a, b, n, m-1, dp), dp[n][m]);
        
        return dp[n][m];
        
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        int ans = helper(nums1, nums2, n-1, m-1, dp);
        return ans;
    }

    //1035 maxUncrossedLines
    public int helper(int[] a, int[] b, int n, int m, int[][] dp){
        if(n == 0 || m ==0){
            return dp[n][m] = 0;
        }
        
        if(dp[n][m] != -1) return dp[n][m];
        if(a[n-1] == b[m-1]){
            return dp[n][m] = helper(a,b, n-1, m-1,dp) +1;
        }else{
            return dp[n][m] = Math.max(helper(a, b, n-1, m, dp), helper(a, b, n, m-1, dp));
        }
    }
    
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        return helper(nums1, nums2, n, m, dp);
    }


    //Count subsequences of type a^i, b^j, c^k gfg
    public int fun(String s)
    {
        // Write your code here
        int a= 0,ab = 0, abc = 0, mod = (int)1e9 + 7;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == 'a'){
                a = ((2*a)% mod + 1) % mod;
            }else if(ch  == 'b'){
                ab = ((2*ab)% mod +a)% mod;
            }else if(ch == 'c'){
                abc = ((2*abc)% mod + ab)% mod;
            }
        }
        return abc % mod;
    }

    // Count palindromic subsequences gfg

    long mod = (long)1e9 + 7;
    
    long cps(String s, int si, int ei , long[][] dp){
        if(si >= ei){
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }
        if(dp[si][ei] != -1) return dp[si][ei] ;
        
        long f = cps(s, si + 1, ei , dp) % mod;
        long sec = cps(s, si , ei -1, dp) % mod;
        if(s.charAt(si) == s.charAt(ei)){
            return dp[si][ei] = (f + sec + 1) % mod;
        }
        long a = cps(s, si + 1, ei -1, dp) % mod;
        return dp[si][ei] = (f + sec - a + mod) % mod;
        
        
    }
    
    long countPS(String str)
    {
        // Your code here
        int n  = str.length();
        long[][] dp = new long[n][n];
        for(long[] a: dp)
            Arrays.fill(a, -1);
        return cps(str, 0, n-1, dp) ;
    }

    //44 Wildcard matching
    public String removestar(String p){
        if(p.length() == 0) return p;
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        int i = 1;
        while(i<p.length()){
            while(i<p.length() && sb.charAt(sb.length() -1) == '*' && p.charAt(i) == '*') i++;
            if(i<p.length()) sb.append(p.charAt(i));
            i++;
        }
        return sb.toString();
    }
    
    public int isMatch(String a, String b, int n, int m, int[][] dp){
        if(n==0 || m==0){
            if(n ==0 && m==0) return dp[n][m] = 1;
            if(m==1 && b.charAt(m-1) == '*') return dp[n][m] = 1;
            return dp[n][m] = 0;
        }
        if(dp[n][m] != -1) return dp[n][m];
        int ch1 = a.charAt(n-1);
        int ch2 = b.charAt(m-1);
        if(ch1 == ch2 || ch2 == '?'){
            return dp[n][m] = isMatch(a,b,n-1, m-1, dp);
        }else if(ch2 == '*'){
            boolean res = false;
            res = res || isMatch(a,b,n-1, m, dp) ==1 ;
            res = res || isMatch(a,b,n, m-1, dp) ==1;
            
            return dp[n][m] = res ? 1 : 0;
        }
        
        return dp[n][m] = 0;
        
    }
    
    public boolean isMatch(String s, String p) {
        p = removestar(p);
        int n = s.length(), m = p.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] a: dp) Arrays.fill(a, -1);
        
        return isMatch(s,p,n,m, dp) == 1;
    }
}
