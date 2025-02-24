import java.io.*;
import java.util.*;
//Increasing Set

public class Main{

public static void main(String[] args){
  Scanner scn = new Scanner(System.in);
  int N = scn.nextInt();
  // int k = scn.nextInt();
  int[] arr = new int[N];
  for(int i=0;i<N;i++){
    arr[i]=scn.nextInt();
  }

  //Longest increasing subseq
  // Brute->  create all subseq then check for inc: if inc update val of omax;
  int ans11 = lis_recurse(arr,0,Integer.MIN_VALUE,0); 
  int ans12 = lis_mem(arr,0,Integer.MIN_VALUE,new int[N]); // thi 
  int ans13 = lis_tab(arr); 
  int ans14 = lis_tab_nlogn(arr); 
  
  //Max sum increasin subseq
  int ans21 = msis_recurse(arr,0,Integer.MIN_VALUE,0); 
  int ans22 = msis_mem(arr,0,Integer.MIN_VALUE,0,new int[N]); 
  int ans23 = msis_tab(arr); 
 
  System.out.println(ans11);
  System.out.println(ans12);
  System.out.println(ans13);
  System.out.println(ans14);

  System.out.println(ans21);
  System.out.println(ans22);
  System.out.println(ans23);

}

//longest inc subseq
public static int lis_recurse(int[]arr, int idx,int prev,int len){
  int n = arr.length;
  if(idx==n){
    return len;
  }

  int len1=0,len2=0;
  if(arr[idx]>prev){
    //included
    len1= lis_recurse(arr, idx+1, arr[idx], len+1);
  }
  //not included
  len2=lis_recurse(arr, idx+1, prev, len);

  
  return Math.max(len1,len2);
}

public static int lis_mem(int[] arr,int idx,int prev,int[] dp){
  int n = arr.length;
  if(idx==n) return 0;
  if(dp[idx]!=0)  return dp[idx];

  int len1=0,len2=0;
  if(arr[idx]>prev){
    //included
    len1= 1+lis_mem(arr, idx+1, arr[idx],dp);
  }
  //not included
  len2=lis_mem(arr, idx+1, prev,dp);

  dp[idx]= Math.max(len1,len2);
  return Math.max(len1,len2);
}

public static int lis_tab(int[] arr){
  int N = arr.length;

  int dp[] = new int[N];
  int max=1;
  for(int i=0;i<N;i++){
    for(int j=i-1;j>=0;j--){
      if(arr[j]<arr[i] && dp[j]>dp[i]){
        dp[i]=dp[j];
      }
    }
    dp[i]++;
    max = Math.max(dp[i],max);
  }

  return max;
}

public static int lis_tab_nlogn(int[] arr){
  if(arr.length==0) return 0;
  
  int n = arr.length;
  int dp[] = new int[n];
  int maxlen=0;
  dp[0]=arr[0];

  for(int i=1;i<n;i++){
      if(arr[i]>dp[maxlen]){
          maxlen++;
          dp[maxlen]= arr[i];
      }else{
          int idx = Bin_search(dp,arr[i],maxlen);
          dp[idx]=arr[i];
      }
  }
  
  return maxlen+1;
} 

public static int Bin_search(int[]dp ,int data,int len){
  
  int prev=0;
  int i=0,j=len;
  while(i<=j){
      int mid = (i+j)/2;
      if(dp[mid]>=data){
          prev=mid;
          j = mid-1;
      }else{
          i= mid+1;
      }
                  
  }
  
  return prev;
}

//----------------------------------------------------------------------------------------------------------------------------------------
//Max sum inc subseq
public static int msis_recurse(int[]arr, int idx,int prev,int sum){
  int n = arr.length;
  if(idx==n){
    return sum;
  }

  int sum1=0,sum2=0;
  if(arr[idx]>prev){
    //included
    sum1= msis_recurse(arr, idx+1, arr[idx], sum+arr[idx]);
  }
  //not included
  sum2=msis_recurse(arr, idx+1, prev, sum);

  
  return Math.max(sum1,sum2);
}

public static int msis_mem(int[] arr,int idx,int prev,int sum,int[] dp){
  int n = arr.length;
  if(idx==n){
    return sum;
  }
  if(dp[idx]!=0){
    return dp[idx];
  } 

  int sum1=0,sum2=0;
  if(arr[idx]>prev){
    //included
    sum1= msis_mem(arr, idx+1, arr[idx], sum+arr[idx],dp);
  }
  //not included
  sum2=msis_mem(arr, idx+1, prev, sum,dp);

  dp[idx]= Math.max(sum1,sum2);
  return Math.max(sum1,sum2);
}

public static int msis_tab(int[] arr){
  int N = arr.length;

  int dp[] = new int[N];
  int max=1;
  for(int i=0;i<N;i++){
    for(int j=i-1;j>=0;j--){
      if(arr[j]<arr[i] && dp[j]>dp[i]){
        dp[i]=dp[j];
      }
    }
    dp[i]+=arr[i];
    max = Math.max(dp[i],max);
  }

  return max;
}



}