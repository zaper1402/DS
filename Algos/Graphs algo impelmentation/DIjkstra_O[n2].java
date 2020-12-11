
import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class Main { 
    static final int V = 9; 
    int minDistance(int dist[], Boolean visited[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++){ 
            if (visited[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            } 
        }
  
        return min_index; 
    } 
  
    
    void printSolution(int dist[]) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < V; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
    } 
  

    void dijkstra(int graph[][], int S) 
    { 
        int dist[] = new int[V]; 
        Boolean visited[] = new Boolean[V]; 
  
        for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            visited[i] = false; 
        } 
  
        dist[S] = 0; 
  
        // Find shortest path for all vertices 
        //O[V-1]
        for (int count = 0; count < V - 1; count++) { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first iteration. 
            //O[V]
            int src = minDistance(dist, visited); 
  
            // Mark the picked vertex as processed 
            visited[src] = true; 
  
            // Update dist value of the adjacent vertices of the  
            //O[V]
            for (int v = 0; v < V; v++) 
  
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!visited[v] && graph[src][v] != 0 && dist[src] != Integer.MAX_VALUE && dist[src] + graph[src][v] < dist[v]) 
                    dist[v] = dist[src] + graph[src][v]; 
        } 
  
        // print the constructed distance array 
        printSolution(dist); 
    } 
  
    public static void main(String[] args) 
    { 

        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
        ShortestPath t = new ShortestPath(); 

        //O[V2]
        t.dijkstra(graph, 0); 
    } 
} 