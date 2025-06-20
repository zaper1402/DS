import java.util.*; 
import java.lang.*; 
import java.io.*; 

class Graph {
    int V, E; 
	Edge edge[];

	Graph(int v, int e) { 
		V = v; 
		E = e; 
		edge = new Edge[E]; 
		for (int i=0; i<e; ++i) 
			edge[i] = new Edge(); 
	} 

	class Edge implements Comparable<Edge> { 
		int src, dest, weight; 
		public int compareTo(Edge compareEdge) { 
			return this.weight-compareEdge.weight; 
		} 
	}; 

	class subset{ 
		int parent, rank; 
	};  
    
	void KruskalMST(){ 
		Edge result[] = new Edge[V];
		int e = 0; 
		int i = 0; 
		for (i=0; i<V; ++i) 
			result[i] = new Edge(); 

		// Step 1: Sort all the edges in non-decreasing order of their 
		Arrays.sort(edge); 

		// Allocate memory for creating V ssubsets 
		subset subsets[] = new subset[V]; 
		for(i=0; i<V; ++i) subsets[i]=new subset(); 

		// Create V subsets with single elements 
		for (int v = 0; v < V; ++v) { 
			subsets[v].parent = v; 
			subsets[v].rank = 0; 
		} 

		i = 0; 

		while (e < V - 1) 
		{ 
			// Step 2: Pick the smallest edge.
			Edge next_edge = edge[i++]; 

			int x = find(subsets, next_edge.src); 
			int y = find(subsets, next_edge.dest); 

			// If including this edge does't cause cycle,  
			if (x != y) 
			{ 
				result[e++] = next_edge; 
				Union(subsets, x, y); 
			} 
		} 

		System.out.println("Following are the edges in " +"the constructed MST"); 
		for (i = 0; i < e; ++i) 
			System.out.println(result[i].src+" -- " + 
				result[i].dest+" == " + result[i].weight); 
    }
    
    int find(subset subsets[], int i){ 
		if (subsets[i].parent != i) 
			subsets[i].parent = find(subsets, subsets[i].parent); 

		return subsets[i].parent; 
	} 

	
	void Union(subset subsets[], int x, int y) { 
		int xroot = find(subsets, x); 
		int yroot = find(subsets, y); 

		if (subsets[xroot].rank < subsets[yroot].rank) 
			subsets[xroot].parent = yroot; 
		else if (subsets[xroot].rank > subsets[yroot].rank) 
			subsets[yroot].parent = xroot; 
		else{ 
			subsets[yroot].parent = xroot; 
			subsets[xroot].rank++; 
		} 
    }

	public static void main (String[] args) 
	{ 

		/* Let us create following weighted graph 
				10 
			0--------1 
			| \	     | 
		   6|  5\    |15 
			|	  \  | 
			2--------3 
				4	 */
		int V = 4;
		int E = 5;
		Graph graph = new Graph(V, E); 

		// add edge 0-1 
		graph.edge[0].src = 0; 
		graph.edge[0].dest = 1; 
		graph.edge[0].weight = 10; 

		// add edge 0-2 
		graph.edge[1].src = 0; 
		graph.edge[1].dest = 2; 
		graph.edge[1].weight = 6; 

		// add edge 0-3 
		graph.edge[2].src = 0; 
		graph.edge[2].dest = 3; 
		graph.edge[2].weight = 5; 

		// add edge 1-3 
		graph.edge[3].src = 1; 
		graph.edge[3].dest = 3; 
		graph.edge[3].weight = 15; 

		// add edge 2-3 
		graph.edge[4].src = 2; 
		graph.edge[4].dest = 3; 
		graph.edge[4].weight = 4; 

		graph.KruskalMST(); 
	} 
} 
