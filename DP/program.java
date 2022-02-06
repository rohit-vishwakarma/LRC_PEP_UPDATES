public class program {
    public int[] LIS(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for(int i=0; i<n; i++){
            dp[i] = 1;
            for(int j = i-1; j>=0 ; j--){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }
}
