import java.io.*;
import java.util.*;

//Range sum queries
/*Ques->
Min
max
sum
GCD/LCM
First element greater than give element
*/

/*
Lchild = 2*i+1;
Rchild = 2*i+2;
parent = (i-1)/2;
*/


public class Main{

  static class segmentTree_min{
    int[] segTree;
    int[] arr;
    int N,size=1;

    segmentTree_min(int[] a){
      arr = a;
      N =a.length;

      while(size<(2*N-1))  size*=2;

      segTree = new int[size];
    }


    public int construct(int idx, int si, int ei){
      if(si==ei){
        segTree[idx]=arr[si];
        return segTree[idx];
      }

      int mid = (si+ei)/2;

      int left = construct(2*idx +1, si, mid);
      int right = construct(2*idx +2, mid+1, ei);

      segTree[idx]= Math.min(left,right);

      return segTree[idx];
    }

    //si,ei -> curr range of tree while l,r-> start&end index
    public int find(int idx, int si,int ei,int l,int r){
      //total overlap
      if(l<=si && ei<=r){
        return segTree[idx];
      }

      //no overlap
      if(l>ei || r<si){
        return Integer.MAX_VALUE;
      }

      int mid = (si+ei)/2;

      int left = find(2*idx+1, si, mid, l, r);
      int right = find(2*idx+2, mid+1, ei, l, r);

      return Math.min(left,right);
    }

    public int update(int i,int si,int ei,int idx,int val){
        if(si==ei){
          arr[idx]=val;
          segTree[i]=arr[idx];
          return segTree[i];
        }

        int mid = (si+ei)/2;

        if(si<=idx && idx<=mid){
          update(2*i+1, si, mid, idx, val);
        }else{
          update(2*i+2, mid+1, ei, idx, val);
        }

        segTree[i] = Math.min(segTree[2*i+1],segTree[2*i+2]);
        return segTree[i];
    }
  }

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    int arr[] = new int[N];
    for(int i=0;i<N;i++){
      arr[i]=scn.nextInt();
    }

    segmentTree_min segTree = new segmentTree_min(arr);
    segTree.construct(0,0,N-1);

    int q = scn.nextInt();
    for(int i=0;i<q;i++){
      int idx = scn.nextInt();
      int val = scn.nextInt();
      int l = scn.nextInt();
      int r = scn.nextInt();

      segTree.update(0,0,N-1,idx, val);
      System.out.println("arr-> "+Arrays.toString(arr));
      System.out.println(segTree.find(0,0,N-1,l,r));
    }



  }
}