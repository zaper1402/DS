public class Floyd_warshal_O[V^3] {
    public static void main (String[] args){
    	 //code
    	 Scanner scn = new Scanner(System.in);
    	 int t = scn.nextInt();
        int V = scn.nextInt();
        int arr[][] = new int[V][V];
        
        //Input graph
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                arr[i][j]= scn.nextInt();
            }
        }
    	     
        //Integermediate Node
        for(int k=0;k<V;k++){
            //row
            for(int i=0;i<V;i++){
                //col
                for(int j=0;j<V;j++){
                    if(arr[i][k]!=Integer.MAX_VALUE && arr[k][j]!=Integer.MAX_VALUE)
                        arr[i][j] = Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }
        

        //Print
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(arr[i][j]==10000000) System.out.print("INF"+" ");
                else System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
            
        
	 
	 }
}
