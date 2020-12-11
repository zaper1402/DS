import java.io.*;
import java.util.*;

public class Main {
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

    //O[ElogV] => as E at worst = V^2
    //O[V2logV] => while:O[V] * (addingnbr: O[V]  + adding in pque: O[logV])
    public static void dijkstra(ArrayList < Edge >[] graph,int src,int vtces){
        int distance[] = new int[vtces];
        HashSet<Integer> visited = new HashSet();
        PriorityQueue < Pair > pque = new PriorityQueue();

        //Pair -> (vtx,psf,cost)
        pque.add(new Pair(src, "" + src, 0));

        //O[V]
        while (visited.size()<vtces && pque.size() > 0) {

            //remove
            Pair temp = pque.remove();

            if (!visited.contains(temp.vtx)) {

                //mark
                visited.add(temp.vtx);
                distance[temp.vtx]=temp.cost;
                System.out.println(temp.vtx+" via " +temp.psf+" @ "+temp.cost);

                //add [ Edge-> (src,nbr,wt)   ]
                //O[V]
                for(Edge e: graph[temp.vtx]){
                    int nbr = e.nbr;
                    //O[logV]
                    if(!visited.contains(nbr)) pque.add(new Pair(nbr,temp.psf+nbr,temp.cost+e.wt));
                }
            }
        }

    }
    
    public static void printDistance(distance){
        for(int i=0;i<distance.length;i++){
            System.out.println(i+"->"+distance[i]);
        }
    }
}
