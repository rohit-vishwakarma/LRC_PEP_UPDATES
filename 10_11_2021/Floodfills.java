public class Floodfills {

    public static ArrayList<String> findPath(int[][] m, int n) {
        // Your code here
        int[][] dir = {{1,0},{0,-1},{0,1},{-1,0}};
        String[] dirS = {"D", "L", "R", "U"};
        ArrayList<String> ans = new ArrayList<>();
        floodfill(0,0,n-1,n-1,m,dir, dirS, "", ans);
        // System.out.println(c);
        return ans;
    }
    
    public static int floodfill(int sr, int sc, int er, int ec, int[][] maze,int[][] dir, String[] dirS, String psf, ArrayList<String> ans){
        if(maze[sr][sc] == 0) return 0;
        if(sr == er && sc == ec){
            ans.add(psf);
            return 1;
        }
        maze[sr][sc] = 0;
        int count =0;
        for(int d = 0; d<dir.length; d++){
            int s = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(s >=0 && c >=0 && s <= er && c <= ec && maze[s][c] == 1){
                count += floodfill(s, c, er, ec, maze, dir, dirS, psf + dirS[d], ans);
            }
        }
        maze[sr][sc] = 1;
        return count;
    }

    //======================================================

    public int[][] dir = {{0,1},{1,0}}; 
    public int mod = (int)1e9+7;
    public int FindWays(int n, int m, int[][] blocked_cells)
    {
        int[][] vis = new int[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        for(int[] d : dp)
            Arrays.fill(d,-1);
        for(int i=0; i<blocked_cells.length; i++){
                int r = blocked_cells[i][0];
                int c = blocked_cells[i][1];
                if(r==1 && c==1) return 0;
                vis[r][c] = 1;
        }
        return mazePath(1,1,n,m,vis, dp);
    }
    public int mazePath(int sr, int sc, int dr, int dc, int[][] vis, int[][] dp){
        if(sr==dr && sc==dc){
            return dp[sr][sc] = 1;
        }
        
        if(dp[sr][sc] != -1) return dp[sr][sc];
        
        vis[sr][sc] = 1;
        
        int count = 0;
        
        for(int i=0; i<2; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];
            if(r<=dr && c<=dc && vis[r][c]!=1){
                count = (count + mazePath(r,c,dr,dc,vis, dp) % mod) % mod;
            }
        }
        
        vis[sr][sc] = 0;
        return dp[sr][sc] = count;
    }
    
    public static void main(String[] args){

    }
}
