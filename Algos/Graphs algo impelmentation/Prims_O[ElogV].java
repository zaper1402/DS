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
    
    public static class Pair implements Comparable<Pair>{
        int vtx;
        String nbr;
        int cost;
        
        public Pair(int vtx,String nbr,int cost){
            this.vtx=vtx;
            this.nbr=nbr;
            this.cost=cost;
        }
        
        public int compareTo(Pair obj){
            return this.cost-obj.cost;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        // write your code here
       
    }


    public static void Prims(){

        boolean visited[] = new boolean[vtces];
        PriorityQueue<Pair> pque = new PriorityQueue<>();

        //Pair -> vtx,parent,cost
        pque.add(new Pair(0,"",0));
        
        while(pque.size()>0){
            
            //remove
            Pair element = pque.remove();
            int src = element.vtx;
            String parent = element.nbr;
            int cost = element.cost;
            
            
            if(!visited[src]){
                //mark
                visited[src]=true;
                if(cost!=0)System.out.println("["+src+"-"+nbrNode+"@"+cost+"]");
                
                //add
                for(Edge e : graph[src]){
                    int nbr = e.nbr;
                    int wt = e.wt;
                   if(!visited[nbr]) pque.add(new Pair(nbr,""+src,wt));
                }
            }
        }



    }
}