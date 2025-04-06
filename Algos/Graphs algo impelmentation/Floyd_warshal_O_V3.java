import java.util.Scanner;

/**
 * Floyd-Warshall Algorithm Implementation
 * This algorithm finds the shortest paths between all pairs of vertices in a weighted graph.
 * 
 * Time Complexity: O(V^3) where V is the number of vertices
 * Space Complexity: O(V^2)
 *
 * This implementation can detect negative cycles in the graph.
 */
public class Floyd_warshal_O_V3 {
    private static final int INF = 10000000;
    
    private static boolean hasNegativeCycle(int[][] dist, int V) {
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }
    
    public static void main (String[] args){
        // Explanation: The Floyd-Warshall algorithm computes the shortest path between every
        // pair of vertices in the graph. It works with both positive and negative edge weights,
        // as long as there are no negative cycles.
        
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        int V = scn.nextInt();
        int[][] arr = new int[V][V];
        
        // Input graph - adjacency matrix representation
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                arr[i][j] = scn.nextInt();
            }
        }
        
        // The core Floyd-Warshall algorithm:
        // For each intermediate vertex k, check if path from i to j can be improved
        // by going through vertex k (i→k→j instead of direct i→j)
        for(int k=0; k<V; k++){
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    // Prevent overflow by checking for INF
                    if(arr[i][k] != INF && arr[k][j] != INF) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }
        
        // Check if the graph contains negative cycles
        if (hasNegativeCycle(arr, V)) {
            System.out.println("Graph contains negative cycle");
        } else {
            // Print the shortest distances
            for(int i=0; i<V; i++){
                for(int j=0; j<V; j++){
                    if(arr[i][j] == INF) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(arr[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
        
        scn.close(); // Close Scanner to prevent resource leak
    }
}
