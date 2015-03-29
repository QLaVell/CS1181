package lab8;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/*
* This purpose of this project is to implement a interpolation search.
*/

/**
 * This class creates an array of random numbers and depending upon user input,
 * returns true or false depending on the success of the search.
 */
public class Lab8 {
    // Create a DataInput object to handle user input.
    static DataInput keyboard = new DataInput();

    public static void main(String[] args) {
        
        // Declare a final variable for the size of the array to search and
        // declare the array.
        final int SIZE = 1000;
        double[] values = new double[SIZE];
        
        // Declare variables used for setting up the search
        int    searchIndex;
        int    searchType = 0;
        double searchValue;
        
        //Fill the array with values
        for(int i = 0; i < values.length; i++){
            values[i] = Math.random() * 1000;
        }
        
        // Sort the array using merge sort
        sortArray(values, 0, values.length - 1);
        do{
            do{
                // Prompt the user for successful or unsuccessful search or quit
                System.out.print("Enter type of search (1=success, 0=fail, -1=quit): ");
                searchType = keyboard.getNextInt();
            }while((searchType != 1) && (searchType != 0) && (searchType != -1));
            // Start search loop
            // Get the index that the user wants to search
            searchIndex = getSearchIndex(searchType, SIZE);
            // If the search index is valid...
            if(searchIndex != -1){
                // Search
                // If the search should return successful, get the value at the
                // index
                if(searchType == 1){
                    searchValue = values[searchIndex];
                    System.out.println("Search for value DATA[" + searchIndex + "] = " + String.format("%.3f", searchValue));
                }else{ // Otherwise get a value that's not in the array.
                    if(searchIndex < SIZE - 1){
                        searchValue = (values[searchIndex] + values[searchIndex + 1])/2;
                        System.out.println("Search for value (DATA[" + searchIndex + "] + DATA[" + (searchIndex + 1) + "])/2 = " + String.format("%.3f", searchValue));
                    }else{
                        searchValue = (values[searchIndex] + (values[searchIndex] + 100))/2;
                        System.out.println("Search for value (DATA[" + searchIndex + "] + DATA[" + (searchIndex) + "] + 100)/2 = " + String.format("%.3f", searchValue));
                    }
                }
                // Print the array
                for(int i = 0; i < values.length; i++){
                    if(i % 10 == 0){
                        System.out.println("");
                    }else{
                        if(values[i] < 10){
                            System.out.print("00");
                        }else if(values[i] < 100){
                            System.out.print("0");
                        }
                        System.out.print(String.format("%4.3f ", values[i]));                        
                    }
                }
                System.out.println("");
                
                // Search and return the result
                System.out.println("Search returns " + search(0, values.length - 1, values, searchValue));
            }
        }while(searchType != -1); // end while
    }
    
    /**
     * Sorts the given array from <i>first</i> to <i>last</i>.
     * Precondition: Array <i>values</i> must exist and <i>first</i> must be less than <i>last</i>.
     * Postcondition: Array <i>values</i> will be sorted.
     * @param values the array to be sorted
     * @param first the starting position to sort
     * @param last the ending position to sort
     */
    public static void sortArray(double[] values, int first, int last){
        // Call the recursive insertion sort method to sort the array.
        insertionSort(values, first, last);
    }//end sortArray

    /**
     * Sorts the given array <i>values</i> using the insertion sort algorithm.
     * Precondition: Array <i>values</i> must exist and <i>first</i> must be less than <i>last</i>.
     * Postcondition: Array <i>values</i> will be sorted.
     * @param values the array to be sorted
     * @param first the starting position to sort
     * @param last the ending position to sort.
     */
    public static void insertionSort(double[] values, int first, int last){
        // If the subarray is at least 1 long, sort the halves.
        if(first < last){
            // sort the rest
            insertionSort(values, first, last - 1);
            // insert the last number into the sorted array.
            insertInOrder(values[last], values, first, last - 1);
        }//end if
    }//end insertionSort
    
    /**
     * Inserts the value <i>val</i> into the correct position in the sorted array.
     * Precondition: Array <i>values</i> must exist and <i>begin</i> must be less than <i>end</i>.
     * Postcondition: A subarray of <i>values</i> will be sorted.
     * @param val the value to be inserted into the sorted list.
     * @param values the array being sorted
     * @param begin the beginning of the unsorted array
     * @param end the end of the unsorted array.
     */
    public static void insertInOrder(double val, double[] values, int begin,
            int end){
        // If the value goes at the end, put it there
        if(val >= values[end]){
            values[end + 1] = val;
        }else if(begin < end){ // If the end hasn't passed the beginning,
            // Move the end down one spot and try to insert value again
            values[end + 1] = values[end];
            insertInOrder(val, values, begin, end - 1);
        }else{ // Otherwise it goes at the beginning
            values[end + 1] = values[end];
            values[end] = val;
        }//end if-else
    }//end insertInOrder
    
    /**
     * Searches the array <i>values</i> for the value <i>searchValue</i>.
     * Precondition: Array <i>values</i> must exist and <i>lowerBound</i> must be less than <i>upperBound</i>.
     * Postcondition: N/A
     * @param lowerBound the index of the beginning of the subarray to search
     * @param upperBound the index of the end of the subarray to search
     * @param values the array to search
     * @param searchValue the value to search for
     * @return true if the value <i>searchValue</i> is found, false otherwise.
     */
    public static boolean search(int lowerBound, int upperBound, 
            double[] values, double searchValue){
        boolean success = false;
        
        // Get the value at the lower and upper bounds
        double lowerValue = values[lowerBound];
        double upperValue = values[upperBound];
        
        // Get the search position
        int searchPosition = (int) ((double) lowerBound + 
                ((searchValue - lowerValue)/(upperValue - lowerValue)) * 
                (double)(upperBound - lowerBound));
        
        // If the search position is inside the subarray, test it.
        if((lowerBound <= searchPosition) && (searchPosition <= upperBound)){
            
            // If the searchValue is equal to the value at the search position
            // then the search is done.
            if(searchValue == values[searchPosition]){
                success = true;
            }else if(searchValue > values[searchPosition]){
                // Search the right half
                if(searchPosition + 1 < upperBound){
                    success = search(searchPosition + 1, upperBound, values, searchValue);
                }else{
                    // search the upperBound position
                    success = search(upperBound, upperBound, values, searchValue);
                }
            }else{
                // search the left half
                success = search(lowerBound, searchPosition - 1, values, searchValue);
            }//end if-else
        }//end if
        
        return success;
    }//end search
    
    /**
     * Prompts the user for and returns the index to search.
     * Precondition: N/A
     * Postcondition: N/A
     * @param searchType the type of search the user wants to perform (successful or fail)
     * @param size the size of the array that will be searched
     * @return the user selected index to search for.
     */
    public static int getSearchIndex(int searchType, int size){
        int index = -1;
        switch(searchType){
            case 1: // Successful search
                // Prompt user for index
                do{
                    System.out.print("Enter index (0..." + (size - 1) + "): ");
                    index = keyboard.getNextInt();
                    if((index < 0) || (index > 999)){
                        System.out.println("That is not a valid index. Please try again");
                    }
                }while((index < 0) || (index > 999));
                break;
            case 0: // Unsuccessful search
                do{
                    System.out.print("Enter index (0..." + (size - 1) + "): ");
                    index = keyboard.getNextInt();
                    if((index < 0) || (index > 999)){
                        System.out.println("That is not a valid index. Please try again");
                    }
                }while((index < 0) || (index > 999));
                break;
            case -1: // Quit
                
                break;
            default: // Other cases ****SHOULD NEVER HAPPEN****
                System.out.println("ERROR!!!");
                break;
        }//end switch
        return index;
    }//end getSearchIndex
}//end class
