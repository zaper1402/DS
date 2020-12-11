// Tarjan's Algorithm

/*
parent[] - stores parent of node
disc[] - stores discorvery time
Low[] - strores low time of node
visited - hashset
Conditions -  1)Root is a articulation point of it haves more than one independent child
              2) disc[src]<=Low[dest]

*/
import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 

class Main{
  static class Graph{ 
    private int V;
    private LinkedList<Integer> adj[]; 
    int time = 0; 
    static final int NIL = -1; 

    
    Graph(int v) { 
      V = v; 
      adj = new LinkedList[v]; 
      for (int i=0; i<v; ++i) 
        adj[i] = new LinkedList(); 
    } 

    void addEdge(int v, int w) { 
      adj[v].add(w);
      adj[w].add(v);
    } 

    //Articulation point
    void DFS(int u, HashSet<Integer> visited, int disc[],int low[], int parent[], HashSet<Integer> ap){ 
          visited.add(u);
          int children=0;

          disc[u]=low[u]=time++;

          for(int v:adj[u]){
              if(!visited.contains(v)){
                  parent[v] = u;
                  children++;

                  DFS(v,visited,disc,low,parent,ap);
                  low[u] = Math.min(low[u],low[v]);

                  if(children>1 && parent[u]==-1){
                      ap.add(u);
                  }

                  if(parent[u]!=-1 && disc[u]<= low[v]){
                      ap.add(u);
                  }
              }else if(v!=parent[u]){
                  low[u] = Math.min(low[u],disc[v]);
              }
          }

    } 

    //function to call AP
    void APConst(){ 
          HashSet<Integer> visited = new HashSet();
          HashSet<Integer> AP = new HashSet<>();
          int[] disc = new int[V];
          int[] parent = new int[V];
          int[] low  =new int[V];

          for(int i=0;i<V;i++){
              disc[i]=-1;
              parent[i]=-1;
              low[i]=-1;
          }

          for(int i=0;i<V;i++){
              if(!visited.contains(i)){
                  DFS(i,visited,disc,low,parent,AP);
              }
          }

          for(int val:AP){
              System.out.println(val);
          }

    } 

    
  }
  public static void main(String args[]) { 
    System.out.println("Articulation points in first graph "); 
    Graph g1 = new Graph(5); 
    g1.addEdge(1, 0); 
    g1.addEdge(0, 2); 
    g1.addEdge(2, 1); 
    g1.addEdge(0, 3); 
    g1.addEdge(3, 4); 
    g1.APConst(); 
    System.out.println(); 

    System.out.println("Articulation points in Second graph"); 
    Graph g2 = new Graph(4); 
    g2.addEdge(0, 1); 
    g2.addEdge(1, 2); 
    g2.addEdge(2, 3); 
    g2.APConst(); 
    System.out.println(); 

    System.out.println("Articulation points in Third graph "); 
    Graph g3 = new Graph(7); 
    g3.addEdge(0, 1); 
    g3.addEdge(1, 2); 
    g3.addEdge(2, 0); 
    g3.addEdge(1, 3); 
    g3.addEdge(1, 4); 
    g3.addEdge(1, 6); 
    g3.addEdge(3, 5); 
    g3.addEdge(4, 5); 
    g3.APConst(); 
  } 
}

