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

    public static class cpair {
        int tc = 0;
        int fc = 0;

        cpair(int t, int f) {
            tc = t;
            fc = f;
        }
    }

    static int mod = 1003;

    public static void Evaluation(cpair l, cpair r, char ch, cpair res) {
        int total = ((l.tc + l.fc) * (r.tc + r.fc)) % mod;
        if (ch == '|') {
            int fc = (l.fc * r.fc) % mod;
            res.fc = (res.fc + fc) % mod;
            res.tc = (res.tc + total - fc + mod) % mod;
        } else if (ch == '&') {
            int tc = (l.tc * r.tc) % mod;
            res.fc = (res.fc + total - tc + mod) % mod;
            res.tc = (res.tc + tc) % mod;
        } else {
            int tc = (l.tc * r.fc) + (l.fc * r.tc);
            res.tc = (res.tc + tc) % mod;
            res.fc = (res.fc + total - tc + mod) % mod;
        }
    }

    public static cpair countWays(String s, int si, int ei, cpair[][] dp) {
        if (si == ei) {
            int t = s.charAt(si) == 'T' ? 1 : 0;
            int f = s.charAt(si) == 'F' ? 1 : 0;
            return dp[si][ei] = new cpair(t, f);
        }
        if (dp[si][ei] != null)
            return dp[si][ei];
        cpair res = new cpair(0, 0);
        for (int cut = si + 1; cut < ei; cut += 2) {
            cpair leftres = countWays(s, si, cut - 1, dp);
            cpair rightres = countWays(s, cut + 1, ei, dp);

            char operator = s.charAt(cut);
            Evaluation(leftres, rightres, operator, res);
        }
        return dp[si][ei] = res;
    }

    public static int countWays(int N, String S) {
        cpair[][] dp = new cpair[N][N];
        return countWays(S, 0, N - 1, dp).tc;
    }

    // 241. Different Ways to Add Parentheses
    public void fill(String s, List<Integer> operand, List<Character> operator) {
        int i = 0;
        while (i < s.length()) {
            int val = 0;
            while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                val = val * 10 + (s.charAt(i) - '0');
                i++;
            }
            operand.add(val);
            if (i < s.length()) {
                operator.add(s.charAt(i));
            }
            i++;
        }
        // System.out.println(operand);
        // System.out.println(operator);
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -(int) 1e9);
        }
        List<Integer> operand = new ArrayList<>();
        List<Character> operator = new ArrayList<>();
        fill(expression, operand, operator);
        List<Integer> ans = helper(operand, operator, 0, operand.size() - 1, 0, operator.size() - 1);

        return ans;
    }

    public List<Integer> helper(List<Integer> operand, List<Character> operator, int ops, int ope, int ots, int ote) {
        if (ops == ope) {
            int val = operand.get(ops);
            List<Integer> base = new ArrayList<>();
            base.add(val);
            return base;
        }
        List<Integer> res = new ArrayList<>();
        for (int cut = ots; cut <= ote; cut++) {
            List<Integer> lf = helper(operand, operator, ops, cut, ots, cut - 1);
            List<Integer> rg = helper(operand, operator, cut + 1, ope, cut + 1, ote);
            char ch = operator.get(cut);
            for (int i = 0; i < lf.size(); i++) {
                int v1 = lf.get(i);
                for (int j = 0; j < rg.size(); j++) {
                    int v2 = rg.get(j);
                    if (ch == '-') {
                        res.add(v1 - v2);
                    } else if (ch == '+') {
                        res.add(v1 + v2);
                    } else if (ch == '*') {
                        res.add(v1 * v2);
                    }
                }
            }
        }
        return res;
    }

    // optimalSearchTree gfg
    static int sumoffreq(int si, int ei, int[] freq) {
        int sum = 0;
        while (si <= ei) {
            sum += freq[si];
            si++;
        }
        return sum;
    }

    static int helper(int[] arr, int[] freq, int si, int ei, int[][] dp) {
        if (dp[si][ei] != -1)
            return dp[si][ei];
        int minAns = (int) 1e9;
        for (int cut = si; cut <= ei; cut++) {
            int lres = cut == si ? 0 : helper(arr, freq, si, cut - 1, dp);
            int rres = cut == ei ? 0 : helper(arr, freq, cut + 1, ei, dp);

            int ans = lres + sumoffreq(si, ei, freq) + rres;
            minAns = Math.min(ans, minAns);
        }
        return dp[si][ei] = minAns;
    }

    static int optimalSearchTree(int keys[], int freq[], int n) {
        // code here
        int[][] dp = new int[n][n];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        return helper(keys, freq, 0, n - 1, dp);
    }

    // 95. Unique Binary Search Trees II
    public static List<TreeNode> helper(int st, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (st == end) {
            TreeNode base = new TreeNode(st);
            res.add(base);
            return res;
        }
        if (st > end) {
            TreeNode base = null;
            res.add(base);
            return res;
        }
        for (int cut = st; cut <= end; cut++) {
            List<TreeNode> lr = helper(st, cut - 1);
            List<TreeNode> rr = helper(cut + 1, end);
            for (var l : lr) {
                for (var r : rr) {
                    TreeNode node = new TreeNode(cut);
                    node.left = l;
                    node.right = r;
                    res.add(node);
                }
            }
        }
        return res;

    }

    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
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
