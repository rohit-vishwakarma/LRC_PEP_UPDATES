import java.util.*;
public class LIS {

    //Longest Increasing Subsequence
    public int[] LIS_LR(int[] arr){ //Left to right
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
            if(maxLen < dp[i]){
                maxLen = dp[i];
            }
        }
        System.out.println(maxLen);
        return dp;
    }

    //Longest Decreasing Subsequence (left to right)
    public int[] LDS(int[] arr){   //Right to left LIS
        int n = arr.length; 
        int[] dp = new int[n];
        int maxLen = 0;
        for(int i = n-1;i>=0; i--){
            dp[i] = 1;
            for(int j= i+1; j<n; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i] , dp[j] +1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return dp;
    }
    //LongestBitonicSequence gfg
    public int LongestBitonicSequence(int[] nums)
    {
        // Code here
        int[] LIS = LIS_LR(nums);
        int[] LDS = LDS(nums);
        int max =0;
        for(int i=0; i<nums.length; i++){
            max = Math.max(max, LDS[i] + LIS[i] -1);
        }
        return max;
    }

    //Building Bridges gfg
    public void maxOverlapBridges(int[][] points){
        int n = points.length;
        int[] dp = new int[n];
        Arrays.sort(points, (a,b)->{  //sorting points by x axis
            return a[0] - b[0];
        });
        int maxLen = 0;
        for(int i =0; i<n; i++){
            dp[i] = 1;
            for(int j = i-1; j>=0; j--){
                if(points[i][1] > points[j][1]){ // if at same points then >= will be used
                    dp[i] = Math.max(dp[i] , dp[j] + 1);
                }
            }
            maxLen = Math.max(dp[i], maxLen);
        }
        System.out.println(maxLen);
    }

    //Minimum number of deletion to be performed to make array sorted
    public int minDel(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        //find lis and subtract it from arr length
        int maxLen = 0;
        for(int i =0; i<n; i++){
            dp[i] = 1;
            for(int j=i-1; j>=0; j--){
                if(arr[i] >= arr[j]){ //increasing and equal to subsequences.
                    dp[i] = Math.max(dp[i], dp[j] +1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return n-maxLen;
    }

    //Maximum sum increasing subsequence
    public int maxSumIS(int arr[], int n)  
	{  
	    //code here.
	     int[] dp = new int[n];

        int maxValue = 0;
        for(int i=0; i<n; i++){
            dp[i] = arr[i];
            int maxEle = 0;
            for(int j = i-1; j>=0 ; j--){
                if(arr[i] > arr[j]){
                    if(maxEle < dp[j]) maxEle = dp[j]; 
                }
            }
            dp[i] += maxEle;
            if(maxValue < dp[i]){
                maxValue = dp[i];
            }
        }
        
        return maxValue;
	}  

    //673 Number of Longest Increasing Subsequence
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        int maxlen = 0, maxCount = 0;
        for(int i=0; i<n; i++){
            dp[i] = 1;
            count[i] = 1;
            for(int j= i-1; j>=0; j--){
                if(nums[i] > nums[j]){
                    if(dp[i] < (dp[j] + 1)){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if(dp[i] == (dp[j] + 1)){
                        count[i] += count[j];
                    }
                }
            }
            if(maxlen < dp[i]){
                 maxlen = dp[i];
                maxCount = count[i];
            }else if(maxlen == dp[i]){
                maxCount += count[i];
            }
        }
        return maxCount;
    }
}
