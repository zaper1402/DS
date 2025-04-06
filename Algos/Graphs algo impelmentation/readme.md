# Graph Algorithms

This repository contains implementations of various graph algorithms with their use cases, time complexities, and space complexities. The algorithms are organized in a recommended learning sequence.

## 1. Basic Graph Traversal

### 1.1 Breadth-First Search (BFS)
- **Implementation**: [BFS.java](./BFS.java)
- **Use Case**: Finds the shortest path in an unweighted graph, level-order traversal, connected components.
- **Time Complexity**: O(V + E) where V is the number of vertices and E is the number of edges.
- **Space Complexity**: O(V) for the queue and visited set.
- **Explanation**: BFS starts at a root vertex and explores all neighbor vertices at the current depth before moving on to vertices at the next depth level. A "connected component" is a subset of vertices such that there is a path between any two vertices in the subset.

### 1.2 Depth-First Search (DFS)
- **Implementation**: [DFS.java](./DFS.java)
- **Use Case**: Topological sorting, cycle detection, path finding, connected components.
- **Time Complexity**: O(V + E) where V is the number of vertices and E is the number of edges.
- **Space Complexity**: O(V) for the recursion stack and visited set.
- **Explanation**: DFS explores as far as possible along each branch before backtracking. It's often implemented using recursion or an explicit stack.

## 2. Graph Structure Analysis

### 2.1 Connected Components
- **Use Case**: Find groups of vertices that are connected to each other.
- **Implementation**: Uses BFS or DFS traversal.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the visited set.

### 2.3 Strongly Connected Components (Directed Graphs)
- **Implementation**: [KosaRaju_O[V+E].java](./KosaRaju_O[V+E].java), [Tarjan_O_VplusE.java](./Tarjan_O[V+E].java)
- **Use Case**: Finds strongly connected components in a directed graph.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the stack and visited arrays.
- **Explanation**: A strongly connected component is a subgraph where every vertex is reachable from every other vertex in that subgraph. Kosaraju's algorithm uses two DFS passes to identify these components. Tarjan's algorithm finds strongly connected components in a single DFS pass by keeping track of the "lowlink" values, making it more efficient in practice though it has the same asymptotic complexity as Kosaraju's algorithm.

### 2.2 Articulation Points and Bridges
- **Implementation**: [Articulation_point_O[V+E].java](./Articulation_point_O[V+E].java)
- **Use Case**: Finds critical nodes and edges in a graph whose removal increases the number of connected components.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the discovery time, low time, and visited arrays.
- **Explanation**: Articulation points (or cut vertices) are vertices that, when removed, increase the number of connected components in the graph. Bridges are edges that, when removed, increase the number of connected components.

## 3. Topological Ordering (Directed Acyclic Graphs)

### 3.1 Topological Sort
- **Implementation**: [Topological_Sort_O[V+E].java](./Topological_Sort_O[V+E].java)
- **Use Case**: Linear ordering of vertices in a directed acyclic graph (DAG).
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the visited array and result array.
- **Explanation**: A topological sort is an ordering of vertices such that for every directed edge (u,v), vertex u comes before v in the ordering. This is only possible in a DAG (Directed Acyclic Graph) - a directed graph with no cycles.

### 3.2 Kahn's Algorithm (Alternate Topological Sort)
- **Implementation**: [Kahn's_Algo_O[V+E].java](./Kahn's_Algo_O[V+E].java)
- **Use Case**: Linear ordering of vertices in a directed acyclic graph using an iterative approach.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the in-degree array and queue.
- **Explanation**: Kahn's algorithm works by repeatedly removing vertices with no incoming edges and adding them to the result. It can also detect cycles in a graph.

## 4. Shortest Path Algorithms

### 4.1 Unweighted Graphs
- **Use Case**: Find shortest path when all edges have equal weight.
- **Implementation**: BFS can be used directly.
- **Time Complexity**: O(V + E)

### 4.2 Weighted Graphs (Non-negative Weights)
#### 4.2.1 Dijkstra's Algorithm
- **Implementation**: [DIjkstra_O[n2].java](./DIjkstra_O[n2].java), [Dijkstra_O_ElogV.java](./Dijkstra_O_ElogV.java)
- **Use Case**: Finds the shortest path from a source vertex to all other vertices in a weighted graph with non-negative weights.
- **Time Complexity**: O((V + E) log V) with a binary heap implementation.
- **Space Complexity**: O(V) for the priority queue and distance array.
- **Explanation**: Dijkstra's algorithm uses a greedy approach to find the shortest path, maintaining a priority queue of vertices by their distance from the source.

#### 4.2.2 A* Search Algorithm
- **Implementation**: [AStar.java](./AStar.java)
- **Use Case**: Finds the shortest path between two vertices using heuristics.
- **Time Complexity**: O(E) in the best case, but can be exponential in the worst case.
- **Space Complexity**: O(V) for storing nodes.
- **Explanation**: A* is an informed search algorithm that uses a heuristic function to estimate the cost from any vertex to the goal, making it more efficient than Dijkstra's algorithm when a specific destination is known.

### 4.3 Weighted Graphs (Potentially Negative Weights)
#### 4.3.1 Bellman-Ford Algorithm
- **Implementation**: [Bellmann_Ford_O[VE].java](./Bellmann_Ford_O[VE].java)
- **Use Case**: Finds the shortest path from a source vertex to all other vertices, works with negative edge weights, detects negative cycles.
- **Time Complexity**: O(V × E)
- **Space Complexity**: O(V) for the distance array.
- **Explanation**: Unlike Dijkstra's algorithm, Bellman-Ford can handle graphs with negative edge weights and detect negative cycles (cycles whose edge weights sum to a negative value).

### 4.4 All-Pairs Shortest Path
#### 4.4.1 Floyd-Warshall Algorithm
- **Implementation**: [Floyd_warshal_O_V3.java](./Floyd_warshal_O_V3.java)
- **Use Case**: Finds the shortest paths between all pairs of vertices in a weighted graph.
- **Time Complexity**: O(V³)
- **Space Complexity**: O(V²) for the distance matrix.
- **Explanation**: This is a dynamic programming approach that gradually improves an estimate of the shortest path between two vertices until the estimate is optimal.

## 5. Minimum Spanning Tree Algorithms

### 5.1 Kruskal's Algorithm
- **Implementation**: [Kruskal_O[ElogE].java](./Kruskal_O[ElogE].java)
- **Use Case**: Finds the Minimum Spanning Tree (MST) of a connected, undirected graph.
- **Time Complexity**: O(E log E) or O(E log V) due to sorting edges.
- **Space Complexity**: O(V + E) for the edge list and union-find data structure.
- **Explanation**: A Minimum Spanning Tree is a subset of edges that forms a tree including all vertices, where the total weight of all edges is minimized. Kruskal's algorithm builds the MST by adding edges in order of increasing weight.
- **Note**: The implementation uses a union-find data structure to efficiently manage and merge disjoint sets of vertices.

### 5.2 Prim's Algorithm
- **Implementation**: [Prims_O[N2].java](./Prims_O[N2].java), [Prims_O[ElogV].java](./Prims_O[ElogV].java)
- **Use Case**: Finds the Minimum Spanning Tree (MST) of a connected, undirected graph.
- **Time Complexity**: O((V + E) log V) with a binary heap.
- **Space Complexity**: O(V) for the priority queue and visited set.
- **Explanation**: Like Kruskal's algorithm, Prim's algorithm finds an MST, but it grows the tree one vertex at a time, starting from an arbitrary root vertex and adding the cheapest connection from the tree to another vertex at each step.

## 6. Network Flow Algorithms

### 6.1 Ford-Fulkerson Method (Edmonds-Karp Implementation)
- **Implementation**: [EdmondKarp_O[E^2V].java](./EdmondKarp_O[E^2V].java)
- **Use Case**: Finds the maximum flow in a flow network.
- **Time Complexity**: O(V × E²) for Edmonds-Karp.
- **Space Complexity**: O(V²) for the residual graph.
- **Explanation**: In a flow network, this algorithm finds the maximum possible flow from a source vertex to a sink vertex. It repeatedly finds augmenting paths and increases the flow until no more augmenting paths exist.

## 7. Special Path Problems

### 7.1 Eulerian Path and Circuit
- **Implementation**: [Eularian_undirected_O(V+E).java](./Eularian_undirected_O(V+E).java)
- **Use Case**: Finds a path/circuit that visits every edge exactly once.
- **Time Complexity**: O(V + E)
- **Space Complexity**: O(V) for the visited set and recursion stack.
- **Explanation**: Eulerian paths and circuits are paths/circuits that visit every edge exactly once. The conditions for existence are described in [Eularian_conditons.txt](./Eularian_conditons.txt).

### 7.2 Hamiltonian Path
- **Implementation**: [Hamiltonian_Path.java](./Hamiltonian_Path.java)
- **Use Case**: Finds a path that visits every vertex exactly once.
- **Time Complexity**: O(2^V)
- **Space Complexity**: O(V) for the visited set and recursion stack.
- **Explanation**: A Hamiltonian path is a path that visits each vertex exactly once. Unlike Eulerian paths, determining whether a Hamiltonian path exists is NP-complete.
