import java.util.*;
public class basic {

    public static Scanner in = new Scanner(System.in);

    public static void PrinDec(int n){ // Decreasing
        if(n==0) return;
        System.out.println(n);
        PrinDec(n-1);
    }
    public static void PrinInc(int n){ //Increasing
        if(n==0) return;
        PrinInc(n-1);
        System.out.println(n);
    }
    public static void PrinDecinc(int n){ //Decreasing and Increasing T(n) - O(n)
        if(n==0) return;
        System.out.println(n);
        PrinDecinc(n-1);
        System.out.println(n);
    }

    public static int Factorial(int n){ //factorial  T - O(n) , S - O(1), stack space = O(n)
        if(n<=1) return 1;
        int  ans = Factorial(n-1);
        return n*ans;
    }

    public static int power(int n, int x){ // power T- O(x), S - O(1) , stack space = O(x)
        if(x == 0) return 1;
        int ans = n*power(n, x-1);
        return ans;
    }

    public static int powerLog(int n, int x){ //power T - O(log x), S- O(1), stack space - O(log x)
        if(x == 0) return 1;
        int ans = powerLog(n, x/2);
        ans = x%2==0 ? ans * ans : ans * ans * n;

        return ans;
    }

    public static void PrintZigZag(int n){ //zigzag T - O(n), S - O(1) , Stack space = O(n)
        if(n==0) return;
        System.out.println("pre "+ n);
        PrintZigZag(n-1);
        System.out.println("in "+n );
        PrintZigZag(n-1);
        System.out.println("Post "+n);
    }

    public static void main(String[] args){
        // PrinDec(10);
        // PrinInc(10);
        // PrinDecinc(10);
        // int res = Factorial(5);
        // System.out.println(res);
        // int res = power(10,5);
        // System.out.println(res);
        // int res = powerLog(10,5);
        // System.out.println(res);
        // PrintZigZag(10);
    }
}
