import java.util.*;

public class Main{
    public static class KStack{
        int data[];
        int next[];
        int top[];
        int free;
        KStack(int k,int n){
            data = new int[n];
            next = new int[n];
            top  = new int[k];
            Arrays.fill(top,-1);
            
            free=0;

            for(int i=0;i<n-1;i++) next[i]=i+1;
            next[n-1]=-1;
        }

        public static void push(int x,int sn){
            if(free==-1){
                System.out.println("OverFlow");
                return;
            }

            data[free] = x;
            int nextIdx = next[free];
            next[free]=top[sn];
            top[sn]=free;
            free = nextIdx;
        }

        public static int pop(int sn){
            if(top==-1){
                System.out.println("UnderFlow");
                return;
            }
            
            int topIdx = top[sn];
            int data = data[topIdx];
            data[topIdx]= 0 ;
            top[sn] = next[topIdx];
            next[topIdx]=free;
            free = topIdx;

            return data;
        }

        public static int peek(int sn){
            if(top==-1){
                System.out.println("UnderFlow");
                return;
            }

            return data[top[sn]];
        }


    }


    public static void main(String args[]){
        int k = 3, n = 10; 
          
        KStack ks = new KStack(k, n); 
  
        ks.push(15, 2); 
        ks.push(45, 2); 
  
        // Let us put some items in stack number 1 
        ks.push(17, 1); 
        ks.push(49, 1); 
        ks.push(39, 1); 
  
        // Let us put some items in stack number 0 
        ks.push(11, 0); 
        ks.push(9, 0); 
        ks.push(7, 0); 
        
  
        System.out.println("Popped element from stack 2 is " + ks.pop(2)); 
        System.out.println("Popped element from stack 1 is " + ks.pop(1)); 
        System.out.println("Popped element from stack 0 is " + ks.pop(0)); 
    }


}