import java.util.*;
public class Main{

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    int N = scn.nextInt();
    N=3;

  //Maze Path Jumps-1 && dir -> H,V,D---------------------------------------------------------------------------------
    System.out.println(mazepath_rec(0, 0, N));
    System.out.println(mazepath_mem(0, 0,N, new int[N][N]));
    System.out.println(mazepath_tab(N));

  //Maze Path Jumps-INF && dir -> H,V,D---------------------------------------------------------------------------------
    System.out.println(mazepath2_rec(0, 0, N));
    System.out.println(mazepath2_mem(0, 0,N, new int[N][N]));
    System.out.println(mazepath2_tab(N));

  //Using Dice reach 0-n  Die value can be any

    // N=10;

    // System.out.println(diepath_rec(N));
    // System.out.println(diepath_mem(N, new int[N+1]));
    // System.out.println(diepath_tab(N));

  //Using Dice reach 0-n  Die value can be any

    N=10;
    int moves[] = {2,3,4,5,4,1,3,3,6,1,6};
    System.out.println(diepath2_rec(0,N,moves));
    System.out.println(diepath2_mem(0,N, moves,new int[N+1]));
    System.out.println(diepath2_tab(N,moves));  

    return;
  }


  //Maze Path Jumps-1 && dir -> H,V,D---------------------------------------------------------------------------------
  public static int mazepath_rec(int i,int j,int N){
     if(i>=N || j>=N) return 0;
     if(i==N-1 && j==N-1) return 1;

     int ans=0;
     //h
      ans+=mazepath_rec(i,j+1,N);
     //v
      ans+=mazepath_rec(i+1,j,N);
     //d
      ans+=mazepath_rec(i+1,j+1,N);

      return ans;
  }

  public static int mazepath_mem(int i,int j,int N,int[][]dp){
     if(i>=N || j>=N) return 0;
     if(i==N-1 && j==N-1) return dp[i][j]=1;
     if(dp[i][j]!=0) return dp[i][j];

     int ans=0;
     //h
      ans+=mazepath_rec(i,j+1,N);
     //v
      ans+=mazepath_rec(i+1,j,N);
     //d
      ans+=mazepath_rec(i+1,j+1,N);

      return  dp[i][j]=ans;
  }

  public static int mazepath_tab(int N){
    int[][]dp = new int[N][N];
    dp[N-1][N-1]=1;

    for(int i=N-1;i>=0;i--){
      for(int j=N-1;j>=0;j--){
          if(i==N-1 && j==N-1){
            dp[i][j]=1;
          }else{
            int right = (j+1<N)? dp[i][j+1]:0;
            int down =  (i+1<N)? dp[i+1][j]:0;
            int dig = (i+1<N && j+1<N)? dp[i+1][j+1]:0;
            dp[i][j] = down + right + dig;
          }
      }
    }

    return dp[0][0];
  }

//Maze Path Jumps-INF && dir -> H,V,D---------------------------------------------------------------------------------
 public static int mazepath2_rec(int i,int j,int N){
    if(i>=N || j>=N) return 0;
    if(i==N-1 && j==N-1) return 1;

    int ans=0;
    //h
    for(int k=1;k+j<N;k++)
     ans+=mazepath2_rec(i,j+k,N);
    //v
    for(int k=1;k+i<N;k++)
     ans+=mazepath2_rec(i+k,j,N);
    //d
    for(int k=1;k+j<N && i+k<N;k++)
     ans+=mazepath2_rec(i+k,j+k,N);

     return ans;
 }

 public static int mazepath2_mem(int i,int j,int N,int[][]dp){
  if(i>=N || j>=N) return 0;
  if(i==N-1 && j==N-1) return 1;
  if(dp[i][j]!=0) return dp[i][j];

  int ans=0;
  //h
  for(int k=1;k+j<N;k++)
   ans+=mazepath2_rec(i,j+k,N);
  //v
  for(int k=1;k+i<N;k++)
   ans+=mazepath2_rec(i+k,j,N);
  //d
  for(int k=1;k+j<N && i+k<N;k++)
   ans+=mazepath2_rec(i+k,j+k,N);

   return dp[i][j]=ans;
 }

 public static int mazepath2_tab(int N){
   int[][]dp = new int[N][N];
   dp[N-1][N-1]=1;

   for(int i=N-1;i>=0;i--){
     for(int j=N-1;j>=0;j--){
         if(i==N-1 && j==N-1){
           dp[i][j]=1;
         }else{
           //h
          for(int k=1;k+j<N;k++)
            dp[i][j]+=dp[i][j+k];
          //v
          for(int k=1;k+i<N;k++)
            dp[i][j]+=dp[i+k][j];
          //d
          for(int k=1;k+j<N && i+k<N;k++)
            dp[i][j]+=dp[i+k][j+k];
         }
     }
   }

   return dp[0][0];
 }


//Using Dice reach 0-n  Die value can be any

 public static int diepath_rec(int N){
  if(N<0) return 0;  
  if(N==0) return 1;
    
    int ans=0;
    for(int i=1;i<=6;i++){
      ans+=diepath_rec(N-i);
    }

    return ans;
 }

 public static int diepath_mem(int N,int dp[]){
    if(N<0) return 0; 
    if(N==0) return dp[N]=1;
    if(dp[N]!=0) return dp[N];

    int ans=0;
    for(int i=1;i<=6;i++){
      ans+=diepath_rec(N-i);
    }

    return dp[N]=ans;
 }

 public static int diepath_tab(int N){
    int dp[] = new int[N+1];
    dp[N]=1;
    for(int i=N;i>=0;i--){
      for(int j=1;j<=6 && i+j<N;i++){
        dp[i]+=dp[i+j];
      }
    }

    return dp[0];
 }

//Using Dice reach 0-n  moves array given

 public static int diepath2_rec(int i,int N,int[]moves){
  if(i>N) return 0;  
  if(i==N) return 1;
    
    int ans=0;
    for(int j=1;j<=moves[i];j++){
      ans+=diepath2_rec(i+j,N,moves);
    }

    return ans;
 }

 public static int diepath2_mem(int i,int N,int moves[],int dp[]){
  if(i>N) return 0;  
  if(i==N) return dp[i]=1;
  if(dp[i]!=0) return dp[i];  
    int ans=0;
    for(int j=1;j<=moves[i];j++){
      ans+=diepath2_mem(i+j,N,moves,dp);
    }

    return dp[i]=ans;
 }

 public static int diepath2_tab(int N,int[] moves){
    int dp[] = new int[N+1];
    dp[N]=1;
    for(int i=N-1;i>=0;i--){
      for(int j=1;j<=moves[i] && i+j<=N;j++){
        dp[i]+=dp[i+j];
      }
    }

    return dp[0];
 } 

}