import java.io.*;
import java.util.*;


public class Main{

  static class Interval{
    int lo;
    int hi;
    Interval(int l,int h){
      this.lo=l;
      this.hi=h;
    }
  }

  static class Node{
    int lo ,hi,max;
    Node left,right;

    Node(int l,int h,int m){
      this.lo = l;
      this.hi = h;
      this.max =m; 
      left=null;
      right=null;
    }
  }

  public static Node insert(Node node,Interval i){
    if(node==null){
      return new Node(i.lo,i.hi,i.hi);
    }

    int l = node.i.low;

    if (i.low < l)
      node.left = insert(node.left, i);
    else
      node.right = insert(node.right, i);
 
    if (node.max < i.high)  node.max = i.high;
 
    return node;
  }

  public static Interval search(Node root, Interval i){
    if (root == null) return null;
 
    if (i.lo < root.hi && i.hi > root.lo) return new Interval(root.lo, root.hi);
 
    if (root.left != null && root.left.max >= i.lo)  return search(root.left, i);
 
    return search(root.right, i);
  }

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();

    //Input
    Interval arr[] = new Interval[N];
    for(int i=0;i<N;i++){
      int a = scn.nextInt();
      int b = scn.nextInt();
      arr[i] = new Interval(a,b);
    }

    Node root = null;
    root = insert(root,arr[0]);

    for(int i=1;i<N;i++){
      //search query
      Interval res = search(root, arr[i]);

      //add the node 
      root = insert(root,arr[i]);
    }

  }


}