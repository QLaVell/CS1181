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
    // Create a DataInput object to handle user input.
    static DataInput keyboard = new DataInput();

    public static void main(String[] args) {
        
        final int SIZE = 1000;
        double[] values = new double[SIZE];
        
        int    searchIndex = 0;
        int    searchType = 0;
        double searchValue = 0.0;
        
        //Fill the array with values
        for(int i = 0; i < values.length; i++){
            values[i] = Math.random() * 1000;
        }
        
        // Sort the array using merge sort
        sortArray(values, 0, values.length - 1);
        do{
            do{
                System.out.print("Enter type of search (1=success, 0=failure, -1=quit): ");
                searchType = keyboard.getNextInt();
            }while((searchType != 1) && (searchType != 0) && (searchType != -1));
            // Start search loop
            searchIndex = getSearchIndex(searchType, SIZE);
            if(searchIndex != -1){
                // Search
                if(searchType == 1){
                    searchValue = values[searchIndex];
                    System.out.println("Search for value DATA[" + searchIndex + "] = " + searchValue);
                }else{
                    if(searchIndex < SIZE - 1){
                        searchValue = (values[searchIndex] + values[searchIndex + 1])/2;
                        System.out.println("Search for value (DATA[" + searchIndex + "] + DATA[" + (searchIndex + 1) + "])/2 = " + searchValue);
                    }else{
                        searchValue = (values[searchIndex] + (values[searchIndex] + 100))/2;
                        System.out.println("Search for value (DATA[" + searchIndex + "] + DATA[" + (searchIndex) + "] + 100)/2 = " + searchValue);
                    }
                }
                System.out.println("Search returns " + search(0, values.length - 1, values, searchValue));
            }
        }while(searchType != -1);
    }
    
    public static void sortArray(double[] values, int first, int last){
        insertionSort(values, first, last);
    }

    public static void insertionSort(double[] values, int first, int last){
        if(first < last){
            insertionSort(values, first, last - 1);
            insertInOrder(values[last], values, first, last - 1);
        }
    }
    
    public static void insertInOrder(double val, double[] values, int begin,
            int end){
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
    
    public static boolean search(int lowerBound, int upperBound, 
            double[] values, double searchValue){
        boolean success = false;
        
        double lowerValue = values[lowerBound];
        double upperValue = values[upperBound];
        int searchPosition = (int) ((double) lowerBound + 
                ((searchValue - lowerValue)/(upperValue - lowerValue)) * 
                (double)(upperBound - lowerBound));
        if((lowerBound <= searchPosition) && (searchPosition <= upperBound)){

            if(searchValue == values[searchPosition]){
                success = true;
            }else if(searchValue > values[searchPosition]){
                if(searchPosition + 1 < upperBound){
                    success = search(searchPosition + 1, upperBound, values, searchValue);
                }else{
                    success = search(upperBound, upperBound, values, searchValue);
                }
            }else{
                success = search(lowerBound, searchPosition - 1, values, searchValue);
            }
        }
        
        return success;
    }
    
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
        }
        return index;
    }
}
