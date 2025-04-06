import java.io.*;
import java.util.*;

/**
 * Depth-First Search (DFS) Implementation
 * 
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the recursion stack and visited set
 * 
 * DFS explores as far as possible along each branch before backtracking
 */
public class DFS {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter number of vertices:");
        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter number of edges:");
        int edges = Integer.parseInt(br.readLine());
        System.out.println("Enter edges as source destination weight:");
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt)); // for undirected graph
        }

        System.out.println("Enter source vertex:");
        int src = Integer.parseInt(br.readLine());
        
        // Call DFS
        boolean[] visited = new boolean[vtces];
        dfs(graph, src, visited, src + "");
        
        // Check for connected components
        ArrayList<ArrayList<Integer>> components = getConnectedComponents(graph);
        System.out.println("Number of connected components: " + components.size());
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }
    }

    /**
     * Depth-First Search algorithm implementation
     * 
     * @param graph The adjacency list representation of the graph
     * @param src The current vertex being visited
     * @param visited Array to track visited vertices
     * @param psf Path so far from the original source
     */
    public static void dfs(ArrayList<Edge>[] graph, int src, boolean[] visited, String psf) {
        // Mark the current vertex as visited
        visited[src] = true;
        
        // Print current vertex and its path from source
        System.out.println(src + " via " + psf);
        
        // Recur for all adjacent vertices that are not visited
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                dfs(graph, e.nbr, visited, psf + e.nbr);
            }
        }
    }
    
    /**
     * Find all connected components in the graph using DFS
     */
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        
        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfsForComponents(graph, v, visited, component);
                components.add(component);
            }
        }
        
        return components;
    }
    
    private static void dfsForComponents(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> component) {
        visited[src] = true;
        component.add(src);
        
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                dfsForComponents(graph, e.nbr, visited, component);
            }
        }
    }
    
    /**
     * Cycle detection using DFS
     */
    public static boolean hasCycle(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        
        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                if (hasCycleHelper(graph, v, visited, -1)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static boolean hasCycleHelper(ArrayList<Edge>[] graph, int src, boolean[] visited, int parent) {
        visited[src] = true;
        
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                if (hasCycleHelper(graph, e.nbr, visited, src)) {
                    return true;
                }
            } else if (e.nbr != parent) {
                // If adjacent vertex is visited and not the parent of current vertex
                // then there is a cycle
                return true;
            }
        }
        
        return false;
    }
}
