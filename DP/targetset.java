import java.util.*;
public class targetset {
    
    public static int permutation_memo(int[] arr, int tar, int[] dp){
        if(tar == 0) return dp[tar] = 1;
        if(dp[tar] != -1) return dp[tar];
        int count = 0;
        for(int ele : arr){
            if(tar - ele >= 0){
                count += permutation_memo(arr, tar - ele, dp);
            }
        }
        return dp[tar] = count;
    }

    public static int combination_memo(int[] arr, int tar, int idx, int[][] dp){
        if(tar == 0) return dp[idx][tar] = 1;
        if(dp[idx][tar] != -1) return dp[idx][tar];
        int count = 0;
        for(int i=idx; i<arr.length; i++){
            if(tar - arr[i] >=0){
                count += combination_memo(arr, tar - arr[i], i, dp);
            }
        }
        return dp[idx][tar] = count;
    }

    public static void display(int[] arr){
        for(int ele: arr){
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    public static void display2D(int[][] arr){
        for(int[] a: arr){
            display(a);
        }
    }
    //322 Coin Change
    public int coinChange(int[] coins, int amount) {
        // int n = coins.length;
        int[] dp = new int[amount+1];
       
        Arrays.fill(dp, (int)1e9);
       
        int ans = coinchange(coins, amount, dp);
        return ans >= (int)1e8 ? -1 : ans;
    }
    public int coinchange(int[] arr,  int tar, int[] dp){
        if(tar == 0 ) return dp[tar] = 0;
        
        if(dp[tar] != (int)1e9) return dp[tar];
        int min = (int)1e8;
        for(int i=0; i<arr.length; i++){
            if(tar - arr[i] >= 0){
                min = Math.min(coinchange(arr, tar - arr[i], dp)+1, min);
            }
        }
        return dp[tar] = min;
       
    }
// target Sum
    public static int targetsum(int[] arr, int tar, int n, int[][] dp){
        if(tar == 0){
            return dp[n][tar] = tar == 0? 1 :0;
        }
        if(dp[n][tar] != -1) return dp[n][tar];
        int count = 0;
        if(tar - arr[n-1] >= 0){
            count += targetsum(arr, tar - arr[n-1], n-1, dp);
        }
        count +=targetsum(arr, tar, n-1, dp);
        return dp[n][tar] = count;
    }

    public static int targetsumDp(int[] arr, int Tar, int N, int[][] dp){
        for(int n =0 ; n<=N; n++){
            for(int tar = 0 ; tar<= Tar; tar++){
                if(tar == 0 || n==0){
                    dp[n][tar] = tar==0?1 : 0;
                    continue;
                }
                int count = 0;
                if(tar - arr[n-1]>=0){
                    count = dp[n-1][tar - arr[n-1]];
                }
                count += dp[n-1][tar];
                dp[n][tar] = count;
            }
        }
        return dp[N][Tar];
    }

    //01 knapsack gfg
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
         // your code here 
         int[][] dp = new int[n+1][W+1];
         for(int[] a: dp){
             Arrays.fill(a, -1);
         }
         return helper(W, wt, val,n, dp);
    } 
    static int helper(int w, int[] wt, int[] v, int idx, int[][] dp){
        if(w == 0 || idx == 0) return dp[idx][w] =  0 ;
        if(dp[idx][w] != -1) return dp[idx][w];
        
        int max = 0;
        if(w - wt[idx - 1] >= 0){
            max = Math.max(max, helper(w- wt[idx - 1], wt, v, idx - 1, dp)+ v[idx -1]);
        }
        max = Math.max(max, helper(w, wt, v, idx - 1, dp));
        return dp[idx][w] = max; 
    }

    //knapsack with duplicate items gfg
    static int knapSackD(int N, int W, int val[], int wt[])
    {
        // code here
        int[][] dp = new int[N+1][W+1];
         for(int[] a: dp){
             Arrays.fill(a, -1);
         }
         return helperD(W, wt, val,N, dp);
    }
    
    static int helperD(int w, int[] wt, int[] v, int idx, int[][] dp){
        if(w == 0 || idx == 0) return dp[idx][w] =  0 ;
        if(dp[idx][w] != -1) return dp[idx][w];
        
        int max = 0;
        if(w - wt[idx - 1] >= 0){
            max = Math.max(max, helperD(w- wt[idx - 1], wt, v, idx , dp)+ v[idx -1]);
        }
        max = Math.max(max, helperD(w, wt, v, idx - 1, dp));
        return dp[idx][w] = max; 
    }

    //416 Partition Equal Subset Sum
    public int noofpart(int[] arr, int n, int tar, int[][] dp){
        if(tar == 0 || n==0) return dp[n][tar] = tar==0? 1: 0;
        if(dp[n][tar] != -1) return dp[n][tar];
        
        boolean res = false;
        if(tar - arr[n-1] >= 0){
            res =res || noofpart(arr, n-1, tar - arr[n-1], dp) == 1;
        }
        res = res || noofpart(arr, n-1, tar, dp) == 1;
        return dp[n][tar] = res ? 1: 0;
    }
    public boolean canPartition(int[] nums) {
        int sum =0;
        for(int ele : nums) sum += ele;
        if(nums.length == 0 || sum%2!=0) return false;
        int n = nums.length;
        int[][] dp = new int[n+1][sum/2 + 1];
        for(int[] a: dp){
            Arrays.fill(a, -1);
        }
        return noofpart(nums, n, sum/2, dp) == 1;
    }

    //494. Target Sum
    public int findallways(int[] arr, int n, int tar, int sum, int[][] dp){
        if(n == 0){
            return dp[n][sum] = tar == sum ? 1: 0;
        }
        
        if(dp[n][sum ] != -1) return dp[n][sum];
        int count = findallways(arr, n-1, tar, sum - arr[n-1], dp);
        count += findallways(arr, n-1, tar, sum + arr[n-1], dp);
        
        return dp[n][sum] = count;
    }
    
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        if(n==0) return 0;
        int sum = 0;
        for(int ele : nums) sum += ele;
        if(sum < target || target < -sum) return 0;
        int[][] dp = new int[n+1][2*sum + 1];
        for(int[] a: dp) Arrays.fill(a, -1);
        return findallways(nums, n, target + sum, sum, dp);
    }

    public static void main(String[] args){
        int[] arr= {2,3,5,7};
        int tar = 10;
        int[][] dp = new int[arr.length][tar+1];
        for(int[] a: dp)
        Arrays.fill(a,-1);
        int ans = combination_memo(arr, tar, 0,dp);
        System.out.println(ans);
        display2D(dp);
    }
}
