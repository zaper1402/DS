import java.io.*;
import java.util.*;

/**
 * Breadth-First Search (BFS) Implementation
 * 
 * Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges
 * Space Complexity: O(V) for the queue and visited set
 * 
 * BFS explores all neighbor vertices at the current depth before moving to vertices at the next depth level
 */
public class BFS {
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

    static class Pair {
        int vtx;
        String psf;  // path so far

        Pair(int vtx, String psf) {
            this.vtx = vtx;
            this.psf = psf;
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
        
        // Call BFS
        bfs(graph, src);
    }

    /**
     * Breadth-First Search algorithm implementation
     * 
     * @param graph The adjacency list representation of the graph
     * @param src The source vertex to start BFS from
     */
    public static void bfs(ArrayList<Edge>[] graph, int src) {
        Queue<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        
        // Add source vertex to queue with its path
        queue.add(new Pair(src, src + ""));
        
        while (!queue.isEmpty()) {
            // Remove front vertex from queue
            Pair rem = queue.remove();
            
            // If vertex is already visited, skip processing it again
            if (visited[rem.vtx]) {
                continue;
            }
            
            // Mark vertex as visited
            visited[rem.vtx] = true;
            
            // Print vertex and its path from source
            System.out.println(rem.vtx + " via " + rem.psf);
            
            // Add all unvisited neighbors to queue
            for (Edge e : graph[rem.vtx]) {
                if (!visited[e.nbr]) {
                    queue.add(new Pair(e.nbr, rem.psf + e.nbr));
                }
            }
        }
    }

    /**
     * Alternative implementation that computes connected components
     */
    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph) {
        boolean[] visited = new boolean[graph.length];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        
        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                ArrayList<Integer> component = new ArrayList<>();
                bfsForComponents(graph, v, visited, component);
                components.add(component);
            }
        }
        
        return components;
    }
    
    private static void bfsForComponents(ArrayList<Edge>[] graph, int src, boolean[] visited, ArrayList<Integer> component) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        
        while (!queue.isEmpty()) {
            int vtx = queue.remove();
            
            if (visited[vtx]) {
                continue;
            }
            
            visited[vtx] = true;
            component.add(vtx);
            
            for (Edge e : graph[vtx]) {
                if (!visited[e.nbr]) {
                    queue.add(e.nbr);
                }
            }
        }
    }
}
