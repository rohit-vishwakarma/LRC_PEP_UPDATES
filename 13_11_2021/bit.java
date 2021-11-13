public class bit {
    public static int onOff(int n , int k ){
        int mask = ~(1<<k);
        return (n&=mask);
    }
    public static int offOn(int n, int k){
        int mask = (1<<k);
        return (n|=mask);
    }
    public static boolean isOn(int n, int k){
        int mask = (1<<k);
        return (n&=mask) != 0;
    }

    public boolean isPowerOfTwo(int n) {
        // return n>0 && (n&(n-1)) == 0;
        while(n>0){
            // System.out.println(n);
            if(n==1) return true;
            if(n%2==1) return false;
            
            n = n>>1;
            
        }
        return false;
    }
// 260
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for(int ele : nums) xor ^= ele;
        
        int mask = (xor&(-xor));
        
        int a =0, b=0;
        for(int ele: nums){
            if((ele & mask) == 0){
                a ^= ele;
            }else{
                b^= ele;
            }
        }
        
        return new int[]{a,b};
    }

    public static void main(String[] args){
        System.out.println(onOff(5,3));
    }
}
