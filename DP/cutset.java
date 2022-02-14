import java.util.*;

public class cutset {
    // Matrix chain multiplication gfg
    static int mcm_memo(int[] arr, int si, int ei, int[][] dp) {
        if (si + 1 == ei) {
            return dp[si][ei] = 0;
        }
        if (dp[si][ei] != -1)
            return dp[si][ei];
        int min = Integer.MAX_VALUE;
        for (int cut = si + 1; cut < ei; cut++) {
            int leftres = mcm_memo(arr, si, cut, dp);
            int rightres = mcm_memo(arr, cut, ei, dp);

            int res = leftres + arr[si] * arr[cut] * arr[ei] + rightres;
            min = Math.min(res, min);
        }
        return dp[si][ei] = min;
    }

    static int matrixMultiplication(int N, int arr[]) {
        // code here
        int[][] dp = new int[N][N];
        for (int[] a : dp)
            Arrays.fill(a, -1);
        return mcm_memo(arr, 0, N - 1, dp);
    }

    // MinMaxEvaluation "1+2*3+4+5*6" gfg
    public static class minMaxPair {
        int minvalue = (int) 1e9;
        int maxvalue = -(int) 1e9;

        minMaxPair() {

        }

        minMaxPair(int mn, int mx) {
            minvalue = mn;
            maxvalue = mx;
        }
    }

    public static minMaxPair Evaluation(minMaxPair lp, minMaxPair rp, char op) {
        if (op == '+') {
            return new minMaxPair(lp.minvalue + rp.minvalue, lp.maxvalue + rp.maxvalue);
        } else {
            return new minMaxPair(lp.minvalue * rp.minvalue, lp.maxvalue * rp.maxvalue);
        }
    }

    public static minMaxPair minMaxEvaluation(String s, int si, int ei, minMaxPair[][] dp) {
        if (si == ei) {
            int val = s.charAt(si) - '0';
            return new minMaxPair(val, val);
        }
        if (dp[si][ei] != null)
            return dp[si][ei];

        minMaxPair myres = new minMaxPair();
        for (int cut = si + 1; cut < ei; cut += 2) {
            minMaxPair lr = minMaxEvaluation(s, si, cut - 1, dp);
            minMaxPair rr = minMaxEvaluation(s, cut + 1, ei, dp);

            minMaxPair ev = Evaluation(lr, rr, s.charAt(cut));
            myres.minvalue = Math.min(myres.minvalue, ev.minvalue);
            myres.maxvalue = Math.max(myres.maxvalue, ev.maxvalue);
        }
        return dp[si][ei] = myres;
    }

    // 312 burst balloons
    public int burst(int[] arr, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0)
            return dp[si][ei];

        int lval = si == 0 ? 1 : arr[si - 1];
        int rval = ei == arr.length - 1 ? 1 : arr[ei + 1];

        int maxcost = 0;
        for (int cut = si; cut <= ei; cut++) {
            int lc = cut == si ? 0 : burst(arr, si, cut - 1, dp);
            int rc = cut == ei ? 0 : burst(arr, cut + 1, ei, dp);

            int val = lc + lval * arr[cut] * rval + rc;
            maxcost = Math.max(maxcost, val);
        }
        return dp[si][ei] = maxcost;
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return burst(nums, 0, n - 1, dp);
    }

    public static class cpair{
        int tc = 0;
        int fc = 0;
        cpair(int t, int f){
            tc = t;
            fc = f;
        }
    }
    static int mod = 1003;
    public static void Evaluation(cpair l , cpair r, char ch, cpair res){
        int total = ((l.tc + l.fc) * (r.tc + r.fc))% mod;
        if(ch == '|'){
            int fc = (l.fc * r.fc) % mod;
            res.fc  = (res.fc + fc) % mod;
            res.tc = (res. tc + total - fc + mod) % mod; 
        }else if(ch == '&'){
            int tc = (l.tc * r.tc) % mod;
            res.fc = ( res.fc + total - tc + mod) % mod;
            res.tc = (res.tc + tc) % mod;
        }else{
            int tc = (l.tc * r.fc) + (l.fc * r.tc);
            res.tc = (res.tc + tc) % mod;
            res.fc = (res.fc + total - tc + mod) % mod;
        }
    }
    public static cpair countWays(String s, int si,int ei, cpair[][] dp){
        if(si == ei){
            int t = s.charAt(si) == 'T' ? 1 : 0;
            int f = s.charAt(si) == 'F' ? 1 : 0;
            return dp[si][ei] = new cpair(t,f);
        }
        if(dp[si][ei] != null) return dp[si][ei];
        cpair res = new cpair(0, 0);
        for(int cut = si + 1; cut<ei; cut+=2){
            cpair leftres = countWays(s, si, cut - 1, dp);
            cpair rightres = countWays(s, cut + 1, ei, dp);
            
            char operator = s.charAt(cut);
            Evaluation(leftres, rightres, operator, res);
        }
        return dp[si][ei] = res;
    }
    public static int countWays(int N, String S){

    }
    public static void main(String[] args) {
        String s = "1+2*3+4*5";
        int n = s.length();
        minMaxPair[][] dp = new minMaxPair[n][n];
        minMaxPair res = minMaxEvaluation(s, 0, n - 1, dp);
        System.out.println(res.minvalue);
        System.out.println(res.maxvalue);
    }
}
