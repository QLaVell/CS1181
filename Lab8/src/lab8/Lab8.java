package lab8;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/*
* [Describe project here]
*/

/**
 * [Describe class here]
 */
public class Lab8 {

    public static void main(String[] args) {
        final int SIZE = 1000;
        double[] values = new double[SIZE];
        
        //Fill the array with values
        for(int i = 0; i < values.length; i++){
            values[i] = Math.random() * 10;
        }
        
        System.out.println("Pre:");
        //Print pre-sort
        for(Double num : values){
            System.out.println(num);
        }
        System.out.println("");
        
        // Sort the array using merge sort
        sortArray(values, 0, values.length - 1);
        
        System.out.println("Post:");
        //Print post-sort
        for(Double num : values){
            System.out.println(num);
        }
        System.out.println("");
    }
    
    public static void sortArray(double[] values, int first, int last){
        insertionSort(values, first, last);
    }
    
    private static void insertionSort(double[] values, int first, int last){
        if(first < last){
            insertionSort(values, first, last - 1);
            
            insertInOrder(values[last], values, first, last -1);
        }
    }
    
    private static void insertInOrder(double val, double[] values, int begin, int end){
        if(val >= values[end]){
            values[end + 1] = val;
        }else if(begin < end){
            values[end + 1] = values[end];
            insertInOrder(val, values, begin, end - 1);
        }else{
            values[end + 1] = values[end];
            values[end] = val;
        }
    }
    
    private boolean interpolationSearch(int lowerBound, int upperBound, 
            double[] values, double searchValue){
        boolean success = false;
        
        double lowerValue = values[lowerBound];
        double upperValue = values[upperBound];
        int searchPos;
        
        return success;
    }
}
