import java.io.*;
import java.util.*;
//Increasing Set

public class Main{

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    int arr[] = new int[N];

    int max = Integer.MIN_VALUE,min = Integer.MAX_VALUE;
    for(int i=0;i<N;i++){
      arr[i]= scn.nextInt();
      max = Math.max(max,arr[i]);
      min = Math.min(min,arr[i]);
    }
    
    //MIN operations to make array
    
    //non-increasing 
    System.out.println( IncDec_NInc_rec(0,arr,max,min) );
    System.out.println( IncDec_NInc_DP(arr,min,max) );
    System.out.println( IncDec_Ninc_Pque(arr,min,max) );



    // non-decreasing
    System.out.println( IncDec_NDec_DP(arr,min,max) );
    System.out.println( IncDec_NDec_Pque(arr,min,max) );

    //strictly increasing
    System.out.println( StrictInc(arr,min,max) );

    //strictly decreasing

  }

  //Min Inc/Dec -> non increasing
  public static int IncDec_NInc_rec(int idx,int[] arr,int end,int start){
      if(idx==arr.length){
        return 0;
      }
      int ans = Integer.MAX_VALUE;
      for(int i=start;i<=end;i++){    
        int val = IncDec_NInc_rec(idx+1, arr,i, start);
        ans = Math.min(ans,val+Math.abs(arr[idx]-i));
      }

      return ans;
  }

  public static int IncDec_NInc_DP(int[] arr,int min,int max){
      int n = arr.length;
      int[][] dp  = new int[n][max+1];

      for(int i=0;i<n;i++){
          int minMoves = Integer.MAX_VALUE;
          for(int j=max;j>=min;j--){
            dp[i][j]=Integer.MAX_VALUE;
            if(i==0){
              dp[i][j] = Math.abs(j-arr[i]);
              continue;
            }
            
              minMoves = Math.min(dp[i-1][j],minMoves);
              dp[i][j] = Math.min(dp[i][j],minMoves+Math.abs(arr[i]-j));
          }
      }

      int ans=Integer.MAX_VALUE;
      for(int j=max;j>=min;j--) {
        ans = Math.min(dp[n-1][j],ans);
      }

      // printDP(dp);
      return ans;
  }

  public static int IncDec_Ninc_Pque(int[] arr,int min ,int max){
    int sum = 0, dif = 0;
 
    PriorityQueue<Integer> pq = new PriorityQueue<>();
 
    for (int i = 0; i < arr.length; i++){
        if (!pq.isEmpty() && pq.element() < arr[i]){
            dif = arr[i] - pq.element();
            sum += dif;
            pq.remove();
            pq.add(arr[i]);
        }
        pq.add(arr[i]);
    }
     
    return sum;
  }

  //Min Inc/Dec -> non Decreasing 

  // public static int IncDec_NDec_rec(int[] arr){

  // }

  // public static int IncDec_NDec_mem(int[] arr){

  // }

  public static int IncDec_NDec_DP(int[] arr,int min,int max){
    int n = arr.length;
    int[][] dp  = new int[n][max+1];

    for(int i=0;i<n;i++){
        int minMoves = Integer.MAX_VALUE;
        for(int j=min;j<=max;j++){
          dp[i][j]=Integer.MAX_VALUE;
          if(i==0){
            dp[i][j] = Math.abs(j-arr[i]);
            continue;
          }
          
            minMoves = Math.min(dp[i-1][j],minMoves);
            dp[i][j] = Math.min(dp[i][j],minMoves+Math.abs(arr[i]-j));
        }
    }

    int ans=Integer.MAX_VALUE;
    for(int j=min;j<=max;j++) {
      ans = Math.min(dp[n-1][j],ans);
    }

    // printDP(dp);
    return ans;
  }

  public static int IncDec_NDec_Pque(int[] arr,int min ,int max){
    int sum = 0, dif = 0;
 
    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
 
    for (int i = 0; i < arr.length; i++){
        if (!pq.isEmpty() && pq.element() > arr[i]){
            dif = pq.element()-arr[i];
            sum += dif;
            pq.remove();
            pq.add(arr[i]);
        }
        pq.add(arr[i]);
    }
     
    return sum;
  }

  //Strictly Increasing
  public static int StrictInc(int[]arr,int min,int max){
    int n = arr.length;
    int[][] dp  = new int[n][max+n];
    for(int[] tmp:dp) Arrays.fill(tmp,max+n);

    for(int j=0;j<max+n;j++){
      dp[0][j]= Math.abs(arr[0]-j);
    }

    for(int i=1;i<n;i++){
        int minMoves = Integer.MAX_VALUE;
        for(int j=min+i;j<max+n;j++){
          dp[i][j]=Integer.MAX_VALUE;
          
          minMoves = Math.min(dp[i-1][j-1],minMoves);
          dp[i][j] = Math.min(dp[i][j],minMoves+Math.abs(arr[i]-j));
        }
    }

    int ans=Integer.MAX_VALUE;
    for(int j=min;j<max+n;j++) {
      ans = Math.min(dp[n-1][j],ans);
    }

    // printDP(dp);
    return ans;
  }

  //Strictly Decreasing - Wrong impelemtation implement corectly?
  public static int StrictDec(int[]arr,int min,int max){
    int n = arr.length;
    int[][] dp  = new int[n][max+n];
    for(int[] tmp:dp) Arrays.fill(tmp,max+n);

    for(int j=0;j<max+n;j++){
      dp[0][j]= Math.abs(arr[0]-j);
    }

    for(int i=1;i<n;i++){
        int minMoves = Integer.MAX_VALUE;
        for(int j=min+i;j<max+n;j++){
          dp[i][j]=Integer.MAX_VALUE;
          
          minMoves = Math.min(dp[i-1][j-1],minMoves);
          dp[i][j] = Math.min(dp[i][j],minMoves+Math.abs(arr[i]-j));
        }
    }

    int ans=Integer.MAX_VALUE;
    for(int j=min;j<max+n;j++) {
      ans = Math.min(dp[n-1][j],ans);
    }

    printDP(dp);
    return ans;
  }


  public static void printDP(int[][] arr){
    for(int[] tmp : arr){
      for(int val :tmp){
        System.out.print(val+" ");
      }
      System.out.println();
    }
  }
}