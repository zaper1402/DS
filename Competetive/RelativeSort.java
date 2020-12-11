package Competetive;

public class RelativeSort {

    public static void main(String[] args) {

        Integer arr[] = new Integer[]{50, 40, 70, 60, 90};
        Integer index[] = new Integer[]{3,  0,  4,  1,  2};
        Integer[] sortOrder = new Integer[arr.length];

        //Method 1
        final List<Integer> arrCpy = Arrays.asList(arr);
        ArrayList<Integer> sortedList = new ArrayList(arrCpy);
        Collections.sort(sortedList, Comparator.comparing(s -> index[arrCpy.indexOf(s)]));
        // printTable("\nSorted by orderArray",orderArray, arrayToBeSorted, sortOrder);




        //Method 2
        // Create index array.
        for(int i=0; i<sortOrder.length; i++){
            sortOrder[i] = i;
        }
        // printTable("\nNot sorted",orderArray, arrayToBeSorted, sortOrder);

        Arrays.sort(sortOrder,new Comparator<Integer>() {   
            public int compare(Integer a, Integer b){
            	System.out.println(":"+(orderArray[a]-orderArray[b]));
                return orderArray[a]-orderArray[b];
            }});


        // Fill array according to sortedOder array;    
        System.out.println(sortedList);
    }
    
}

