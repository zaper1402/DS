import java.io.*;
import java.util.*;
//Increasing Set

public class Main{

public static void main(String[] args){
  Scanner scn = new Scanner(System.in);
  String str1 = scn.nextLine();
  String str2 = scn.nextLine();

  //Longest commom subseq
  int ans11 = lcs_recurse(str1,str2); 
  int ans12 = lcs_mem(str1,str2,0,0,new int[str1.length()][str2.length()]); 
  int ans13 = lcs_tab(str1,str2); 
  
  
  System.out.println(ans11);
  System.out.println(ans12);
  System.out.println(ans13);

}

//Longest common subseq
public static int lcs_recurse(String str1,String str2){
  if(str1.length()==0 || str2.length()==0){
    return 0;
  }
  int len1 = 0,len2=0;
  if(str1.charAt(0)==str2.charAt(0)){
     return 1+ lcs_recurse(str1.substring(1), str2.substring(1));
  }else{
     len1 = lcs_recurse(str1.substring(1), str2);
     len2 = lcs_recurse(str1, str2.substring(1));

    return Math.max(len1,len2);
  }

}

public static int lcs_mem(String str1,String str2,int idx1,int idx2,int[][] dp){
  if(str1.length()<=idx1 || str2.length()<=idx2)  return 0;

  if(dp[idx1][idx2]!=0) return dp[idx1][idx2];

  int len1 = 0,len2=0,len=0;
  if(str1.charAt(idx1)==str2.charAt(idx2)){
     len= 1+ lcs_mem(str1, str2,idx1+1,idx2+1,dp);
  }else{
     len1 = lcs_mem(str1, str2,idx1+1,idx2,dp);
     len2 = lcs_mem(str1, str2,idx1,idx2+1,dp);

    len =  Math.max(len1,len2);
  }
  return dp[idx1][idx2]=len;
}

public static int lcs_tab(String str1,String str2){
  int n = str1.length();
  int m= str2.length();
  int dp[][] = new int[n+1][m+1];

  for(int i=1;i<=n;i++){
    for(int j=1;j<=m;j++){
      char ch1 = str1.charAt(i-1);
      char ch2 = str2.charAt(j-1);
      if(ch1==ch2){
        dp[i][j]=dp[i-1][j-1]+1;
      }else{
        dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
      }
    }
  }

  return dp[n][m];
}

//

}