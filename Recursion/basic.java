import java.util.*;

public class basic {

    public static Scanner in = new Scanner(System.in);

    public static void PrinDec(int n) { // Decreasing
        if (n == 0)
            return;
        System.out.println(n);
        PrinDec(n - 1);
    }

    // =================================================================================================================================
    public static void PrinInc(int n) { // Increasing
        if (n == 0)
            return;
        PrinInc(n - 1);
        System.out.println(n);
    }

    // =================================================================================================================================
    public static void PrinDecinc(int n) { // Decreasing and Increasing T(n) - O(n)
        if (n == 0)
            return;
        System.out.println(n);
        PrinDecinc(n - 1);
        System.out.println(n);
    }

    // =================================================================================================================================
    public static int Factorial(int n) { // factorial T - O(n) , S - O(1), stack space = O(n)
        if (n <= 1)
            return 1;
        int ans = Factorial(n - 1);
        return n * ans;
    }

    // =================================================================================================================================
    public static int power(int n, int x) { // power T- O(x), S - O(1) , stack space = O(x)
        if (x == 0)
            return 1;
        int ans = n * power(n, x - 1);
        return ans;
    }

    // =================================================================================================================================
    public static int powerLog(int n, int x) { // power T - O(log x), S- O(1), stack space - O(log x)
        if (x == 0)
            return 1;
        int ans = powerLog(n, x / 2);
        ans = x % 2 == 0 ? ans * ans : ans * ans * n;

        return ans;
    }

    // =================================================================================================================================
    public static void PrintZigZag(int n) { // zigzag T - O(n), S - O(1) , Stack space = O(n)
        if (n == 0)
            return;
        System.out.println("pre " + n);
        PrintZigZag(n - 1);
        System.out.println("in " + n);
        PrintZigZag(n - 1);
        System.out.println("Post " + n);
    }

    // =================================================================================================================================
    public static void toh(int n, int src, int des, int help) {
        if (n == 0) {
            System.out.println("Base");
            return;
        }
        System.out.println("Pre : " + src + "  " + des + "  " + help + " with " + n);
        toh(n - 1, src, help, des);
        System.out.println(n + " -> [ " + src + " -> " + des + " ]");
        toh(n - 1, help, des, src);
        System.out.println("Post : " + src + "  " + des + "  " + help + " with " + n);
    }

    // =================================================================================================================================
    public static ArrayList<String> gss(String str, String asf, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add(asf);
            return base;
        }
        char c = str.charAt(idx);
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> s1 = gss(str, asf + c, idx + 1);
        for (String ch : s1) {
            ans.add(ch);
        }
        ArrayList<String> s2 = gss(str, asf, idx + 1);
        for (String ch : s2) {
            ans.add(ch);
        }
        return ans;
    }

    // =================================================================================================================================
    public static String[] keypad = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static ArrayList<String> getKPC(String str, String asf, int idx) {
        if (idx == str.length()) {
            ArrayList<String> base = new ArrayList<>();
            base.add(asf);
            return base;
        }
        int key = (int) (str.charAt(idx) - '0');
        String val = keypad[key];
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < val.length(); i++) {
            char ch = val.charAt(i);
            ArrayList<String> sm = getKPC(str, asf + ch, idx + 1);
            for (String s : sm) {
                ans.add(s);
            }
        }
        return ans;
    }

    // =================================================================================================================================
    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 1; n - i >= 0 && i <= 3; i++) {
            ArrayList<String> sm = getStairPaths(n - i);
            for (String s : sm) {
                ans.add(i + s);
            }
        }
        return ans;
    }

    // =================================================================================================================================
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        if (sc + 1 <= dc) {
            ArrayList<String> h = getMazePaths(sr, sc + 1, dr, dc);
            for (String s : h) {
                ans.add("h" + s);
            }
        }
        if (sr + 1 <= dr) {
            ArrayList<String> v = getMazePaths(sr + 1, sc, dr, dc);
            for (String s : v) {
                ans.add("v" + s);
            }
        }
        return ans;
    }
    // =================================================================================================================================

    public static ArrayList<String> getMazePathsWithJump(int sr, int sc, int dr, int dc) {
        if (sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans = new ArrayList<>();
        for (int j = 1; j + sc <= dc; j++) {
            ArrayList<String> h = getMazePathsWithJump(sr, sc + j, dr, dc);
            for (String s : h) {
                ans.add("h" + j + s);
            }
        }
        for (int j = 1; j + sr <= dr; j++) {
            ArrayList<String> v = getMazePathsWithJump(sr + j, sc, dr, dc);
            for (String s : v) {
                ans.add("v" + j + s);
            }
        }
        for (int j = 1; j + sr <= dr && j + sc <= dc; j++) {
            ArrayList<String> d = getMazePathsWithJump(sr + j, sc + j, dr, dc);
            for (String s : d) {
                ans.add("d" + j + s);
            }
        }
        return ans;
    }

    // =================================================================================================================================
    public static void printPermutations(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            printPermutations(str.substring(0, i) + str.substring(i + 1), asf + ch);
        }
    }
    // =================================================================================================================================
    public static void printEncodings(String str,String asf, int idx) {
        if(idx == str.length()){
            System.out.println(asf);
            return;
        }
        if(str.charAt(idx) == '0'){
            return;
        }
        int v1 = (int)(str.charAt(idx) - '0');
        printEncodings(str, asf + (char)(v1 + 'a' - 1) , idx+1);
        if(idx+1 < str.length()){
            int v2 = v1 * 10 + (int)(str.charAt(idx+1) - '0');
            if(v2<=26){
                printEncodings(str, asf + (char)(v2 + 'a' - 1), idx+2);
            }
        }
        
    }
    // =================================================================================================================================
    
    public static void main(String[] args) {
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
        toh(3, 10, 11, 12);
        // System.out.println(gss("abc", "", 0));
        // System.out.println(getKPC("78", "", 0));
    }
}
