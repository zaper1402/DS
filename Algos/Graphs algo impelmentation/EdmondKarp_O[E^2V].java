import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.LinkedList; 
  
class MaxFlow { 
    static final int V = 6;
  
    /* Returns "true" if there is a path from source 's' to sink 
      't' in residual graph. Also fills parent[] to store the 
      path */
    boolean bfs(int rGraph[][], int s, int t, int parent[]) 
    { 
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; ++i) 
            visited[i]=false; 
  
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
  
        while (queue.size()!=0) { 
            int u = queue.poll(); 
  
            for (int v=0; v<V; v++){ 
                if (visited[v]==false && rGraph[u][v] > 0){ 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
  
        return (visited[t] == true); 
    } 
  
    int fordFulkerson(int graph[][], int s, int t){ 
        int u, v; 
  
        // Create a residual graph and fill the residual graph with given capacities in the original graph as residual capacities in residual graph 
  
        // Residual graph where rGraph[i][j] indicates residual capacity of edge from i to j 
        // (if there is an edge. If rGraph[i][j] is 0, then there is not) 
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
  
        // This array is filled by BFS and to store path 
        int parent[] = new int[V]; 
  
        int max_flow = 0;  // There is no flow initially 
  
        // Augment the flow while tere is path from source 
        // to sink 
        while (bfs(rGraph, s, t, parent)) 
        { 
            // Find minimum edge capacity
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]){ 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]); 
            } 
  
            // update residual capacities of the edges and 
            // reverse edges along the path 
            for (v=t; v != s; v=parent[v]) { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            // Add path flow to overall flow 
            max_flow += path_flow; 
        } 
  
        // Return the overall flow 
        return max_flow; 
    } 
  
    public static void main (String[] args) throws java.lang.Exception { 
        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0}, 
                                     {0, 0, 10, 12, 0, 0}, 
                                     {0, 4, 0, 0, 14, 0}, 
                                     {0, 0, 9, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 4}, 
                                     {0, 0, 0, 0, 0, 0} 
                                   }; 
        MaxFlow m = new MaxFlow(); 
  
        System.out.println("The maximum possible flow is "+m.fordFulkerson(graph, 0, 5)); 
  
    } 
} 