import java.util.*;

public class Main{

    //O[N]
    public static int construct(int[] sgt,int idx,int[] arr ,int l,int r){

        if(l==r){
            sgt[idx]=arr[l];
            return arr[l];
        }

        int mid = (l+r)/2;
        sgt[idx] +=construct(sgt,2*idx+1,arr,l,mid) + construct(sgt,2*idx+2,arr,mid+1,r);

        return sgt[idx];
    }

    //O[logN]
    public static int find(int[] sgt,int idx,int sl,int sr,int l,int r){
        //sl-> left we want to find
        //sr -> right we want to find

        //total overlap
        if(sl<=l && r<=sr){
            return sgt[idx];
        }

        //No overlap
        if(l>sr || r<sl){
            return 0;
        }

        //partial overlap 
        int mid = (l+r)/2;
        return find(sgt,2*idx+1,sl,sr,l,mid)+find(sgt,2*idx+2,sl,sr,mid+1,r);


    }

    //O[logN]
    public static void update(int[] sgt,int idx,int l,int r,int val,int updateIdx){
        if(updateIdx<l || updateIdx>r) return;
        else sgt[idx]+=val;

        if(l==r) return;

        int mid = (l+r)/2;

        //left
        update(sgt,2*idx+1,l,mid,val,updateIdx);
        //right
        update(sgt,2*idx+2,mid+1,r,val,updateIdx);

    }

    public static void main(String[] args){
        int arr[] = {1,2,5,6,7,-1};
        int N=arr.length;

        int size=1;
        while(size<2*N-1){
            size= size<<1;
        }

        int[] sgt = new int[size];

        //create seg tree
        construct(sgt,0,arr,0,N-1);
        System.out.println(Arrays.toString(sgt));

        //range query
        int lft = 0;
        int rgt = 4;
        System.out.println(find(sgt,0,lft,rgt,0,N-1));

        //update
        int newVal = 5;
        int idxUpdate = 1;
        int diff = newVal-arr[idxUpdate];

        update(sgt,0,0,N-1,diff,idxUpdate);
        System.out.println(Arrays.toString(sgt));
    }
}