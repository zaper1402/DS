import java.io.*; 
  
/**
 * Bellman-Ford Algorithm Implementation
 * Time Complexity: O(VE) where V is number of vertices and E is number of edges
 * 
 * Purpose:
 * - Finds shortest paths from a source vertex to all other vertices
 * - Can handle graphs with negative weight edges
 * - Can detect negative weight cycles in the graph
 * 
 * Key advantages over Dijkstra's algorithm:
 * - Works with negative edge weights
 * - Detects negative cycles (where Dijkstra's fails)
 */
class Bellman_Ford_O { 
    // A class to represent a weighted edge in graph 
    static class Edge { 
        int src, dest, weight; 
        Edge() 
        { 
            src = dest = weight = 0; 
        } 
    }
  
    // Creates a graph with V vertices and E edges 
    static class Graph{
        
        int V, E; 
        Edge edge[];

        Graph(int v, int e) 
        { 
            V = v; 
            E = e; 
            edge = new Edge[e]; 
            for (int i = 0; i < e; ++i) 
                edge[i] = new Edge(); 
        } 
    }
  
    /** 
     * Algorithm steps:
     * 1. Initialize distances from source to all vertices as infinite
     * 2. Set distance to source vertex as 0
     * 3. Relax all edges |V|-1 times (where |V| is the number of vertices)
     * 4. Check for negative-weight cycles
     */
    public static void BellmanFord(Graph graph, int S) { 
        int V = graph.V;
        int E = graph.E;
        int[] dist = new int[V];
        
        // Step 1: Initialize distances from source to all vertices as infinite
        for(int i=0;i<V;i++) dist[i]=Integer.MAX_VALUE;
        
        // Step 2: Set distance to source vertex as 0
        dist[S]=0;

        // Step 3: Relax all edges |V|-1 times
        // This guarantees shortest paths if there are no negative cycles
        for(int i=0;i<V-1;i++){
            for(int j=0;j<E;j++){
                int src= graph.edge[j].src;
                int dest = graph.edge[j].dest;
                int weight = graph.edge[j].weight;

                // Relaxation operation - update if shorter path found
                if(dist[src]!=Integer.MAX_VALUE) dist[dest] = Math.min(dist[src]+weight,dist[dest]);
            }
        }

        // Step 4: Check for negative-weight cycles
        // If we can relax an edge one more time, then there's a negative cycle
        for (int j = 0; j < E; ++j) { 
            int u = graph.edge[j].src; 
            int v = graph.edge[j].dest; 
            int weight = graph.edge[j].weight; 
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) { 
                System.out.println("Graph contains negative weight cycle"); 
                return; 
            } 
        } 

        printArr(dist,V);
    } 

    public static void printArr(int dist[], int V) { 
        System.out.println("Vertex Distance from Source"); 
        for (int i = 0; i < V; ++i) 
            System.out.println(i + "\t\t" + dist[i]); 
    } 
  
    // Driver method to test above function 
    public static void main(String[] args) { 
                int V = 5; // Number of vertices in graph 
        int E = 8; // Number of edges in graph 
  
        Graph graph = new Graph(V, E); 
  
        // add edge 0-1 (or A-B in above figure) 
        graph.edge[0].src = 0; 
        graph.edge[0].dest = 1; 
        graph.edge[0].weight = -1; 
  
        // add edge 0-2 (or A-C in above figure) 
        graph.edge[1].src = 0; 
        graph.edge[1].dest = 2; 
        graph.edge[1].weight = 4; 
  
        // add edge 1-2 (or B-C in above figure) 
        graph.edge[2].src = 1; 
        graph.edge[2].dest = 2; 
        graph.edge[2].weight = 3; 
  
        // add edge 1-3 (or B-D in above figure) 
        graph.edge[3].src = 1; 
        graph.edge[3].dest = 3; 
        graph.edge[3].weight = 2; 
  
        // add edge 1-4 (or A-E in above figure) 
        graph.edge[4].src = 1; 
        graph.edge[4].dest = 4; 
        graph.edge[4].weight = 2; 
  
        // add edge 3-2 (or D-C in above figure) 
        graph.edge[5].src = 3; 
        graph.edge[5].dest = 2; 
        graph.edge[5].weight = 5; 
  
        // add edge 3-1 (or D-B in above figure) 
        graph.edge[6].src = 3; 
        graph.edge[6].dest = 1; 
        graph.edge[6].weight = 1; 
  
        // add edge 4-3 (or E-D in above figure) 
        graph.edge[7].src = 4; 
        graph.edge[7].dest = 3; 
        graph.edge[7].weight = -3; 
  
        BellmanFord(graph, 0); 
    } 
}