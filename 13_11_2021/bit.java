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

    public static void main(String[] args){
        System.out.println(onOff(5,3));
    }
}
