import java.util.*;

public class Main{
    public static class KQueues{
        int data[];
        int next[];
        int front[],end[];
        int free;
        KQueues(int k,int n){
            data = new int[n];
            next = new int[n];
            top  = new int[k];
            end  = new int[k];
            Arrays.fill(top,-1);
            Arrays.fill(end,-1);
            free=0;

            for(int i=0;i<n-1;i++) next[i]=i+1;
            next[n-1]=-1;
        }

        public void enqueue(int x,int sn){
            if(free==-1){
                System.out.println("OverFlow");
                return;
            }

            data[free] = x;

            if(front[sn]==-1 && end[sn]==-1 ){
                front[sn]=free;
                end[sn]=free;
            }
            else{
                next[end[sn]]=free;
                end[sn]=free;
            }

            int nextIdx = next[free];
            next[free]=-1;
            free = next[free];
        }

        public int dequeue(int sn){
            if(front[sn]==-1){
                System.out.println("UnderFlow");
                return  -1;
            }
            
            int frnt = front[sn];
            int value = data[frnt];
            data[frnt]=0;
            
            front[sn]=next[frnt];
            next[frnt]=free;
            free = frnt;

            return value;
        }

        public int peek(int sn){
            if(front[sn]==-1){
                System.out.println("UnderFlow");
                return -1;
            }

            return data[top[sn]];
        }


    }


    public static void main(String args[]){
        int k = 3, n = 10;  
        KQueues ks=  new KQueues(k, n);  
         
          
        // Let us put some items in queue number 2  
        ks.enqueue(15, 2);  
        ks.enqueue(45, 2);  
        
        // Let us put some items in queue number 1  
        ks.enqueue(17, 1);  
        ks.enqueue(49, 1);  
        ks.enqueue(39, 1);  
        
        // Let us put some items in queue number 0  
        ks.enqueue(11, 0);  
        ks.enqueue(9, 0);  
        ks.enqueue(7, 0);  
          
        System.out.println("Dequeued element from queue 2 is " +  
                                ks.dequeue(2));  
        System.out.println("Dequeued element from queue 1 is " +  
                                ks.dequeue(1));  
        System.out.println("Dequeued element from queue 0 is " +  
                                ks.dequeue(0) ); 
    }


}