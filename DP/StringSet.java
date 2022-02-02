import java.util.*;

public class StringSet {
    // 516 Longest Palindromic Subsequence
    public int helper(String s, int si, int ei, int[][] dp) {

        if (si >= ei) {
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }
        if (dp[si][ei] != -1)
            return dp[si][ei];
        if (s.charAt(si) != s.charAt(ei)) {
            dp[si][ei] = Math.max(helper(s, si + 1, ei, dp), helper(s, si, ei - 1, dp));
        } else {
            dp[si][ei] = helper(s, si + 1, ei - 1, dp) + 2;
        }
        return dp[si][ei];
    }

    public int lpss_DP(String s, int SI, int EI, int[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int si = 0, ei = gap; ei < n; si++, ei++) {
                if (si >= ei) {
                    dp[si][ei] = (si == ei) ? 1 : 0;
                    continue;
                }

                if (s.charAt(si) != s.charAt(ei))
                    dp[si][ei] = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                else
                    dp[si][ei] = dp[si + 1][ei - 1] + 2;
            }
        }

        return dp[SI][EI];
    }

    public String lpss_ReverseEngg(String s, int si, int ei, int[][] dp) { // DP should be filled already
        if (si >= ei) {
            return (si == ei) ? (s.charAt(si) + "") : "";
        }
        if (s.charAt(si) == s.charAt(ei)) {
            return s.charAt(si) + lpss_ReverseEngg(s, si + 1, ei - 1, dp) + s.charAt(ei);
        } else if (dp[si + 1][ei] > dp[si][ei - 1]) {
            return lpss_ReverseEngg(s, si + 1, ei, dp);
        } else {
            return lpss_ReverseEngg(s, si, ei - 1, dp);
        }

    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        int ans = helper(s, 0, n - 1, dp);
        String a = lpss_ReverseEngg(s, 0, n - 1, dp);
        System.out.println(a);
        return ans;
    }

    // 1143 longestCommonSubsequence
    public int helper1(String s1, String s2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = 0;
        }
        if (dp[n][m] != -1) {
            return dp[n][m];
        }
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = helper1(s1, s2, n - 1, m - 1, dp) + 1;
        } else {
            return dp[n][m] = Math.max(helper1(s1, s2, n - 1, m, dp), helper1(s1, s2, n, m - 1, dp));
        }
        // return dp[n][m];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    // 72 edit distance
    public int helper(String a, String b, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m : n;
        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (a.charAt(n - 1) == b.charAt(m - 1)) {
            return dp[n][m] = helper(a, b, n - 1, m - 1, dp);
        }

        int insert = helper(a, b, n, m - 1, dp);
        int delete = helper(a, b, n - 1, m, dp);
        int replace = helper(a, b, n - 1, m - 1, dp);

        return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return helper(word1, word2, n, m, dp);
    }

    // Minimum number of deletions and insertions. gfg
    public int minOp(String a, String b, int n, int m, int[][] dp) {
        if (n == 0 || m == 0) {
            return dp[n][m] = (n == 0) ? m : n;
        }
        if (dp[n][m] != -1)
            return dp[n][m];

        if (a.charAt(n - 1) == b.charAt(m - 1)) {
            return dp[n][m] = minOp(a, b, n - 1, m - 1, dp);
        }

        int insert = minOp(a, b, n, m - 1, dp);
        int delete = minOp(a, b, n - 1, m, dp);

        return dp[n][m] = Math.min(insert, delete) + 1;
    }

    public int minOperations(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return minOp(str1, str2, n, m, dp);
    }

    //583 delete operation of two strings
    public int minD(String a, String b, int n, int m, int[][] dp){
        if(n ==0  || m==0){
            return dp[n][m] = n==0 ? m: n;
        }
        if(dp[n][m] != -1) return dp[n][m];
        
        if(a.charAt(n-1) == b.charAt(m-1)){
            return dp[n][m] = minD(a, b, n-1, m-1, dp);
        }
        
        int ad = minD(a,b,n, m-1, dp);
        int bd = minD(a,b,n-1, m, dp);
        
        return dp[n][m] = Math.min(ad, bd) +1;
    }
    
    public int minDistance02(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        return minD(word1, word2, n, m, dp);
    }

    // 115 Distinct subsequences
    public int numDis(String s, String t, int n, int m,int[][] dp){
        if(n<m || m==0){
            return dp[n][m] = (m==0) ? 1 : 0;
        }
        if(dp[n][m] != -1) return dp[n][m];
        if(s.charAt(n-1) == t.charAt(m-1)){
            int f = numDis(s, t, n-1, m-1, dp);
            int sec = numDis(s, t, n-1, m, dp);
            
            return dp[n][m] = f+sec;
        }
        return dp[n][m] = numDis(s,t, n-1, m, dp);
    }
    
    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        return numDis(s, t, n, m, dp);
    }
}
