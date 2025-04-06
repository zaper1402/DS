import java.util.*;

public class Tarjan_O_VplusE {
    private int V;
    private List<List<Integer>> adj;
    private int time;
    private int[] disc;
    private int[] low;
    private boolean[] stackMember;
    private Stack<Integer> stack;
    private List<List<Integer>> stronglyConnectedComponents;

    public Tarjan_O(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<>());
        disc = new int[V];
        low = new int[V];
        stackMember = new boolean[V];
        stack = new Stack<>();
        stronglyConnectedComponents = new ArrayList<>();
        time = 0;
        
        // Initialize discovery time and lowlink values
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    // Function to find all SCCs using Tarjan's algorithm
    public List<List<Integer>> findSCCs() {
        // Call the recursive helper function to find SCCs
        for (int i = 0; i < V; i++)
            if (disc[i] == -1)
                SCCUtil(i);
        return stronglyConnectedComponents;
    }

    // A recursive function that finds SCCs using DFS
    private void SCCUtil(int u) {
        // Set the discovery time and lowlink value
        disc[u] = low[u] = ++time;
        stack.push(u);
        stackMember[u] = true;

        // Go through all adjacent vertices
        for (Integer v : adj.get(u)) {
            // If v is not visited yet, recur for it
            if (disc[v] == -1) {
                SCCUtil(v);
                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);
            }
            // Update low value of u only if v is still in stack (i.e. it's in the current SCC)
            else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // If u is the head node of an SCC
        if (low[u] == disc[u]) {
            List<Integer> component = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                stackMember[w] = false;
                component.add(w);
            } while (w != u);
            stronglyConnectedComponents.add(component);
        }
    }

    public static void main(String[] args) {
        // Create a graph with 8 vertices
        Tarjan_O graph = new Tarjan_O(8);

        // Add edges to form a connected graph with multiple SCCs
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 6);

        System.out.println("Strongly Connected Components:");
        List<List<Integer>> sccs = graph.findSCCs();
        for (int i = 0; i < sccs.size(); i++) {
            System.out.print("SCC " + (i+1) + ": ");
            for (int node : sccs.get(i)) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
