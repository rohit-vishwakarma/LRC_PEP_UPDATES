public class Nqueen {
    
    public static boolean IsQueenSafe(int[][] chess, int row, int col) {
        // write your code here
        int[][] dir = {{0,-1}, {-1,-1},{-1, 0}, {-1 , 1},{0,1}, {1,1},{1,0}, {1,-1}};
        int n = chess.length, m = chess[0].length;
        for(int[] d : dir){
            int r = row + d[0];
            int c = col + d[1];
            while(r>=0 && c>=0 && r<n && c<m){
                if(chess[r][c] > 0) return false;
                
                r += d[0];
                c += d[1];
            }
        }
        return true;
    }
    
    public static void display(int[][] chess){
        int n = chess.length, m = chess[0].length;
        for(int r = 0; r<n; r++){
            for(int c =0; c<m; c++){
                if(chess[r][c] > 0){
                    System.out.print("q"+chess[r][c]+"\t");
                }else{
                    System.out.print("-\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        // write your code here
        if(qpsf == tq){
            display(chess);
            return;
        }
        
        for(int i =0; i < chess.length * chess[0].length ; i++){
            int r = i / chess[0].length;
            int c = i % chess[0].length;
            
            if(chess[r][c] == 0 && IsQueenSafe(chess, r,c)){
                chess[r][c] = qpsf + 1;
                nqueens(qpsf +1, tq, chess);
                chess[r][c] = 0;
            }
        }
    }
    public static void main(String[] args){

    }
}
