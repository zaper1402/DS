
import java.util.*;
import java.lang.*;
import java.io.*;
 
class Main {
    private static final int V = 5;
 
    int minKey(int key[], Boolean mstSet[])
    {
    
        int min = Integer.MAX_VALUE, min_index = -1;
 
        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    
    void printMST(int parent[], int graph[][])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }
 
    
    void primMST(int graph[][])
    {
        // Array to store constructed MST
        int parent[] = new int[V];
 
        // Key values used to pick minimum weight edge in cut
        int key[] = new int[V];
 
        // To represent set of vertices included in MST
        Boolean mstSet[] = new Boolean[V];
 
        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
 
        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is
        // picked as first vertex
        parent[0] = -1; 
 
        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices
            // not yet included in MST
            int src = minKey(key, mstSet);
 
            // Add the picked vertex to the MST Set
            mstSet[src] = true;
 
            for (int v = 0; v < V; v++)
 
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[src][v] != 0 && mstSet[v] == false && graph[src][v] < key[v]) {
                    parent[v] = src;
                    key[v] = graph[src][v];
                }
        }
 
        // print the constructed MST
        printMST(parent, graph);
    }
 
    public static void main(String[] args)
    {
        /* Let us create the following graph
        2 3
        (0)--(1)--(2)
        | / \ |
        6| 8/ \5 |7
        | /     \ |
        (3)-------(4)
            9         */
        MST t = new MST();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                                      { 2, 0, 3, 8, 5 },
                                      { 0, 3, 0, 0, 7 },
                                      { 6, 8, 0, 0, 9 },
                                      { 0, 5, 7, 9, 0 } };
 
        // Print the solution
        t.primMST(graph);
    }
}