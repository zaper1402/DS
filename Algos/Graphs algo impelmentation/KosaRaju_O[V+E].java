import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 
public class Main{
    static class Graph 
    { 
        private int V;   // No. of vertices 
        private LinkedList<Integer> adj[]; //Adjacency List 

        Graph(int v) { 
            V = v; 
            adj = new LinkedList[v]; 
            for (int i=0; i<v; ++i) 
                adj[i] = new LinkedList(); 
        } 

        void addEdge(int v, int w)  { adj[v].add(w); } 

        void DFSPrint(int v,boolean visited[]) { 
            visited[v]=true;
            System.out.print(v+" ");

            for(int val:adj[v]){
                if(!visited[val]){
                    DFSPrint(val,visited);
                }
            }
        } 

        // Function that returns reverse of this graph 
        Graph getTranspose() { 
            Graph g = new Graph(V); 
            for (int v = 0; v < V; v++) 
            { 
                // Recur for all the vertices adjacent to this vertex 
                Iterator<Integer> i =adj[v].listIterator(); 
                while(i.hasNext()) 
                    g.adj[i.next()].add(v); 
            } 
            return g; 
        } 

        void dfsFillStack(int v, boolean visited[], Stack<Integer> stack) { 
        
            visited[v]= true;

            for(int nbr:adj[v]){
                if(!visited[nbr]){
                    dfsFillStack(nbr,visited,stack);
                }
            }

            stack.add(v);
        } 


        void KosaRajus(){ 
            Stack<Integer> stack = new Stack(); 
            boolean visited[] = new boolean[V]; 
            
            
            for (int i = 0; i < V; i++) 
                if (visited[i] == false) 
                dfsFillStack(i, visited, stack); 

            // Create a reversed graph 
            Graph gr = getTranspose(); 


            // Mark all the vertices as not visited (For second DFS) 
            for (int i = 0; i < V; i++) 
                visited[i] = false; 

            // Now process all vertices in order defined by Stack 
            while (stack.empty() == false){

                int v = (int)stack.pop(); 

                if (visited[v] == false) {
                    //Print each scc 
                    gr.DFSPrint(v, visited); 
                    System.out.println(); 
                } 
            } 
        } 

        
    } 

    // Driver method 
    public static void main(String args[]) { 
        Graph g = new Graph(5); 
        g.addEdge(1, 0); 
        g.addEdge(0, 2); 
        g.addEdge(2, 1); 
        g.addEdge(0, 3); 
        g.addEdge(3, 4); 

        System.out.println("Following are strongly connected components "+ 
                        "in given graph "); 
        g.KosaRajus(); 
    } 
}