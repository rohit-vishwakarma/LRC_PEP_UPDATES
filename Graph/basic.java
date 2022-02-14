import java.util.*;
public class basic {
    public static class Edge{
        int des;
        int wt;
        Edge(int d, int w){
            des = d;
            wt = w;
        }
    }
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }
    public static void main(String[] args){
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
    }
}
