import java.io.*;
import java.util.*;
//Increasing Set

public class Main{

  public static void main(String[] args){
    Scanner scn = new Scanner(System.in);
    String str = scn.next();
    int N = str.length();


  //Count distinct subsequence
  int ans11 = countSubseq(str);
    
  //Count Distinct Palindromic subseq -> IMP to understand maths
    int ans12 = countPalindromeSubseq(str);
    
    System.out.println(ans11);
    System.out.println(ans12);

  }

  //Count Distinct  Subseq
  public static int countSubseq(String str){
        int n = str.length();
        long dp[] = new long[n+1];
        dp[0]=1;
        
        
        HashMap<Character,Integer> Hmap = new HashMap();
        for(int i=1;i<=n;i++){
            char ch = str.charAt(i-1);
            dp[i] = 2*dp[i-1];
            if(Hmap.containsKey(ch)){
                int j = Hmap.get(ch);
                dp[i]-=dp[j-1];
            }
            
            Hmap.put(ch,i);
        }
        
        System.out.println(dp[n]-1);
        return 0;
  }


  //Count Distinct Palindromic Susbeq
  public static int countPalindromeSubseq(String str){
    return 0;
  }
}