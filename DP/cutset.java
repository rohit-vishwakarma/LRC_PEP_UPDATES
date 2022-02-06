import java.util.*;
public class cutset {
    //Matrix chain multiplication gfg
    static int mcm_memo(int[] arr, int si, int ei, int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei] !=  -1) return dp[si][ei];
        int min = Integer.MAX_VALUE;
        for(int cut = si + 1; cut<ei; cut++){
            int leftres = mcm_memo(arr, si, cut, dp);
            int rightres = mcm_memo(arr, cut, ei, dp);

            int res = leftres + arr[si]*arr[cut]*arr[ei] + rightres;
            min = Math.min(res, min);
        }
        return dp[si][ei] = min;
    }
    static int matrixMultiplication(int N, int arr[])
    {
        // code here
        int[][] dp = new int[N][N];
        for(int[] a: dp) Arrays.fill(a, -1);
        return mcm_memo(arr,0,N-1, dp);
    }

}
