package lab8;

import java.util.ArrayList;


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
        double[] values = new double[10];
        double[] temp = new double[10];
        
        //Fill the array with values
        for(int i = 0; i < values.length; i++){
            values[i] = Math.random() * 10;
        }
        
        System.out.print("Pre:");
        //Print pre-sort
        for(Double num : values){
            System.out.print(num + " ");
        }
        System.out.println("");
        
        // Sort the array using merge sort
        sortArray(values, temp, 0, values.length - 1);
        
        System.out.println("Post:");
        //Print post-sort
        for(Double num : values){
            System.out.print(num + " ");
        }
        System.out.println("");
    }
    
    public static void sortArray(double[] values, 
            double[] tempArray, int first, int last){
        if(first < last){
            // Get the middle item. In case of even array middle is left side
            int mid = first + (last - first)/2;
            sortArray(values, tempArray, first, mid);
            sortArray(values, tempArray, mid + 1, last);
            merge(values, tempArray, first, mid, last);
        }
        
    }
    
    private static void merge(double[] values, 
            double[] tempArray, int first, int mid, int last){
        int firstHalfBegin = first;
        int firstHalfEnd = mid;
        int secondHalfBegin = mid + 1;
        int secondHalfEnd = last;
        
        int index = 0;
        
        while((firstHalfBegin <= firstHalfEnd) && (secondHalfBegin <= secondHalfEnd)){
            if(values[firstHalfBegin] <= values[secondHalfBegin]){
                tempArray[index] = values[firstHalfBegin];
                firstHalfBegin++;
            }else{
                tempArray[index] = values[secondHalfBegin];
                secondHalfBegin++;
            }//end if-else
            index++;
        }//end while
        
        while(firstHalfBegin < firstHalfEnd){
            tempArray[index] = values[firstHalfBegin];
            firstHalfBegin++;
            index++;
        }//end while
        
        while(secondHalfBegin < secondHalfEnd){
            tempArray[index] = values[secondHalfBegin];
            secondHalfBegin++;
            index++;
        }//end while
        
        for(int i = first; i <= last; i++){
            values[i] = tempArray[i];
        }//end for
    }

}
