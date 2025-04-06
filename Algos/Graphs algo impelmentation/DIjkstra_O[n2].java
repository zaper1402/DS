import java.util.*; 
import java.lang.*; 
import java.io.*; 

/**
 * Implementation of Dijkstra's algorithm with O(V²) time complexity
 * This algorithm finds the shortest path from a source vertex to all other vertices
 * in a weighted graph represented as an adjacency matrix.
 */
class Dijkstra_O_V2 { 
    // Number of vertices in the graph
    static final int V = 9; 
        static int minDistance(int dist[], Boolean visited[]) 
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
  
    
    static void printSolution(int dist[]) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < V; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
    } 
  
    
    static void dijkstra(int graph[][], int S) 
    { 
        int dist[] = new int[V];
        Boolean visited[] = new Boolean[V];
  
                for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            visited[i] = false;
        } 
  
        // Distance from source to itself is always 0
        dist[S] = 0; 
  
        // Find shortest path for all vertices
        // Overall time complexity: O(V²)
        for (int count = 0; count < V - 1; count++) { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed - O(V)
            int src = minDistance(dist, visited); 
  
            // Mark the picked vertex as processed 
            visited[src] = true; 
  
            // Update distance values of adjacent vertices of the picked vertex - O(V)
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if:
                // 1. Vertex v is not visited
                // 2. There is an edge from src to v (graph[src][v] != 0)
                // 3. Path through src to v exists (dist[src] != Integer.MAX_VALUE)
                // 4. Path is shorter than current value of dist[v]
                if (!visited[v] && 
                    graph[src][v] != 0 && 
                    dist[src] != Integer.MAX_VALUE && 
                    dist[src] + graph[src][v] < dist[v]) {
                    dist[v] = dist[src] + graph[src][v]; 
                }
            }
        } 
  
        // Print the calculated shortest path distances
        printSolution(dist); 
    } 
  
        public static void main(String[] args) 
    { 
        
        int graph[][] = new int[][] {             
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }         }; 

        //O[V2]
        dijkstra(graph, 0); 
    } 
}