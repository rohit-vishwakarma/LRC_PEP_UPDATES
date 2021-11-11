public class l001{

    // PNC
    public static int permutationInfi(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0) {
                count += permutationInfi(arr, tar - ele, ans + ele);
            }
        }
        return count;
    }

    public static int combinationInfi(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationInfi(arr, tar - arr[i], i, ans + arr[i]);
        }
        return count;
    }
    public static int combinationSingle(int[] arr, int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += combinationSingle(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
        return count;
    }

    public static int permutationSingle(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                int val = arr[i];

                arr[i] = -arr[i];
                count += permutationSingle(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    public static void PNC(){
        int[] arr = {2,3,5,7};
        int tar = 10;
        String psf = "";
        System.out.println(permutationInfi(arr, tar, psf)+"\nPI\n");
        System.out.println(combinationInfi(arr, tar,0, psf)+"\nCI\n");
        System.out.println(combinationSingle(arr, tar,0, psf)+"\nCS\n");
        System.out.println(permutationSingle(arr, tar, psf)+"\nPS\n");
    }
    
    // Subsequence
    public static int permutationInfiSS(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += permutationInfiSS(arr, tar - arr[idx], 0, ans + arr[idx]);
        count += permutationInfiSS(arr, tar, idx + 1, ans);

        return count;
    }
    public static int combinationInfiSS(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationInfiSS(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationInfiSS(arr, tar, idx + 1, ans);

        return count;
    }

    public static int permutationSingleSS(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationSingleSS(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationSingleSS(arr, tar, idx + 1, ans);
        return count;
    }

    public static int combinationSingleSS(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationSingleSS(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        count += combinationSingleSS(arr, tar, idx + 1, ans);

        return count;
    }

    public static void SS(){
        int[] arr = {2,3,5,7};
        int tar = 10;
        String psf = "";
        System.out.println(permutationInfiSS(arr, tar,0, psf)+"\nPI\n");
        System.out.println(combinationInfiSS(arr, tar,0, psf)+"\nCI\n");
        System.out.println(combinationSingleSS(arr, tar,0, psf)+"\nCS\n");
        System.out.println(permutationSingleSS(arr, tar,0, psf)+"\nPS\n");
  
    }

    public static void main(String[] args){
        PNC();
        System.out.println("SubSequence");
        SS();
    }
}