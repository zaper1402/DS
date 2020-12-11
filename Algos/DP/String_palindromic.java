import java.io.*;
import java.util.*;
//Increasing Set

public class Main{

public static void main(String[] args){
  Scanner scn = new Scanner(System.in);
  String str = scn.next();
  int N = str.length();


//LPS longest palindromic subsequence
  //Brute -> check all susbeq and check if palindrome 
  int ans11 = LPS_recurse(str);
  int ans12 = LPS_mem(0,N-1,str,new int[N][N]);
  int ans13 = LPS_tab(str);
  
//Count Palindromic subseq -> IMP to understand maths
  int ans21 = countPS(str);

//Palindromic Susbtring : Count,Length,String
  //Brute -> produce each susbtring and check palindrome O[n3]
  LPSS(str);

  
  System.out.println(ans11);
  System.out.println(ans12);
  System.out.println(ans13);
  
  System.out.println(ans21);

}

//longest Palindromic Susbeq
public static int LPS_recurse(String str){
  if(str.length()<=1) return str.length();

  int ans = 0;
  if(str.charAt(0)==str.charAt(str.length()-1))
    ans = 2+ LPS_recurse(str.substring(1, str.length()-1));
  else{
    ans = Math.max(LPS_recurse(str.substring(1, str.length())) ,LPS_recurse(str.substring(0, str.length()-1)) );
  }
  
  return ans;

}

public static int LPS_mem(int i,int j, String str,int[][] dp){
  if(i==j) return dp[i][j]=1;
  if(i>j) return dp[i][j]=0;

  if(dp[i][j]!=0) return dp[i][j];

  int ans = 0;
  if(str.charAt(i)==str.charAt(j))  ans = 2+LPS_mem(i+1,j-1,str,dp);
  else{
    ans = Math.max(LPS_mem(i+1,j,str,dp) ,LPS_mem(i,j-1,str,dp) );
  }
  
  return dp[i][j]=ans;
}

public static int LPS_tab(String str){
  int N = str.length();
  int dp[][] = new int[N][N];

  for(int gap=0;gap<N;gap++){
    for(int i=0,j=gap;j<N;j++,i++){
      if(gap==0) {
        dp[i][j]=1;
      }else if(str.charAt(i)==str.charAt(j))  dp[i][j] = 2+dp[i+1][j-1];
      else{
        dp[i][j] = Math.max(dp[i+1][j] ,dp[i][j-1] );
      }

    }
  }

  return  dp[0][N-1];
}

//count Palindromic Subseq
public static int countPS(String str){
  int N = str.length();
      int dp[][]  = new int[N][N];
      
      for(int gap=0;gap<N;gap++){
          for(int i=0,j=gap;j<N;j++,i++){
              if(i==j){
                  dp[i][j]=1;
                  continue;
              }
              int a = dp[i+1][j-1];
              int b = dp[i+1][j];
              int c = dp[i][j-1];
              
              if(str.charAt(i)==str.charAt(j)){
                  dp[i][j] = b+c+1;
              }else{
                  dp[i][j] = b+c-a;
              }
          }
      }
      
      return dp[0][N-1];
}

// LPSS Longest palindromic Substring-> all in one
  // -> Count no of Palindromic Substring
  // -> Length of LPSS
  // -> Print LPSS
public static int[][] LPSS(String str){
int N = str.length();
int dp[][] = new int[N][N];

int count =0;
int Maxlength=1;
String LPSS = "";

for(int gap=0;gap<N;gap++){
  for(int i=0,j=gap;j<N;j++,i++){
      if(gap==0){
        dp[i][j]=1;
        count++;
      }else if(gap==1 && str.charAt(i)==str.charAt(j)){
        dp[i][j]=2;
        count++;
      }else if(str.charAt(i)==str.charAt(j) && dp[i+1][j-1]>0){
        dp[i][j]=2+dp[i+1][j-1];
        count++;
      }

      if(dp[i][j]>Maxlength){
        Maxlength = dp[i][j];
        LPSS = str.substring(i,j+1);
      }
      
  }
}


System.out.println(count);
System.out.println(Maxlength);
System.out.println(LPSS);
return dp;

}

}