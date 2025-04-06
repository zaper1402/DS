import java.io.*;
import java.util.*;

public class Dijkstra_O_ElogV {
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

    public static class Pair implements Comparable<Pair> {
        int vtx;
        int cost;
        String psf;

        public Pair(int vtx, String psf, int cost) {
            this.vtx = vtx;
            this.psf = psf;
            this.cost = cost;

        }

        public int compareTo(Pair obj) {
            return this.cost - obj.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList < Edge > [] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList < > ();
        }

        Pair[] cost = new Pair[vtces];
        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = Integer.parseInt(br.readLine());
        
        //Your code
        dijkstra(graph,src,vtces);
    }

    /*
     * Dijkstra's Algorithm
     * 1. Create a priority queue to store the vertices based on their distance from the source vertex.
     * 2. Initialize the distance of the source vertex to 0 and all other vertices to infinity.
     * 3. Add the source vertex to the priority queue.
     * 4. While the priority queue is not empty, do the following:
     *    a. Remove the vertex with the minimum distance from the priority queue.
     *   b. For each neighbor of the removed vertex, calculate the distance from the source vertex to the neighbor.
     *  c. If the calculated distance is less than the current distance of the neighbor, update the distance and add the neighbor to the priority queue.
     * 5. Repeat steps 4 until all vertices have been processed.
     * 6. The distance array will contain the shortest distance from the source vertex to all other vertices.
     * 7. Print the distance array.
     * 8. Time Complexity: O(ElogV) where E is the number of edges and V is the number of vertices.
     *      Reason: Each edge is processed once, and the priority queue operations take O(logV) time.
     *      Though the while loop runs V times, each edge is processed once.
     *      Hence, the overall time complexity is O(ElogV).
     * 9. Space Complexity: O(V) for the distance array and O(V) for the priority queue.
     */
    public static void dijkstra(ArrayList < Edge >[] graph,int src,int vtces){
        int distance[] = new int[vtces];
        HashSet<Integer> visited = new HashSet();
        PriorityQueue < Pair > pque = new PriorityQueue();

        //Pair -> (vtx,psf,cost)
        pque.add(new Pair(src, "" + src, 0));

        //O[V]
        while (visited.size()<vtces && pque.size() > 0) {

            //remove O[logV]
            Pair temp = pque.remove();

            if (!visited.contains(temp.vtx)) {

                //mark
                visited.add(temp.vtx);
                distance[temp.vtx]=temp.cost;
                System.out.println(temp.vtx+" via " +temp.psf+" @ "+temp.cost);

                //add [ Edge-> (src,nbr,wt)   
                for(Edge e: graph[temp.vtx]){
                    int nbr = e.nbr;
                    //O[logV]
                    if(!visited.contains(nbr)) pque.add(new Pair(nbr,temp.psf+nbr,temp.cost+e.wt));
                }
            }
        }
        
        // Print the final distances
        printDistance(distance);
    }
    
    public static void printDistance(int[] distance){
        for(int i=0;i<distance.length;i++){
            System.out.println(i+"->"+distance[i]);
        }
    }
}
