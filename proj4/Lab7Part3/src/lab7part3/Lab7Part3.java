package lab7part3;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * Finds the minimum number of postage stamps needed to mail a package that
 * requires less than $10 in postage the dynamic algorithm.
 */
public class Lab7Part3 {

    public static void main(String[] args) {
        // Create a DataInput object for keyboard input
        DataInput keyboard = new DataInput();
        
        // Create a boolean for the continue option
        boolean cont = true;
        
        // Create an array that will hold the answers that have already been
        // solved
        int[] solutions = new int[1001];
        
        // Create an array that holds the values of the stamps
        int[] stampValues = {1, 2, 3, 4, 5, 10, 17, 25, 27, 41, 42, 59, 60, 62,
            63, 72, 75, 80, 84, 94, 100};
        
        // Set the index of each stamp value to 1 in the solutions array
        for(int stamp : stampValues){
            solutions[stamp] = 1;
        }//end for
        
        // While the user wants to continue
        while(cont){
            // Initialize the variables
            int answer = 0;
            // Create a variable to hold the total to find the # of stamps for
            int total = -1;
            
            while((total <= 0) || (total > 1000)){
                // Prompt user for amount
                System.out.print("Enter the postage amount to find the minimum " +
                        "number of stamps: ");
                total = keyboard.getNextInt();
                if((total <= 0) || (total > 1000)){
                    System.out.println("Postage amount must be greater than 0 " +
                            "and less than 1000");
                }//end if
            }//end while
            
            // If the answer is already in the lookup table take that
            if(solutions[total] != 0){
                answer = solutions[total];
            }else{// Otherwise, calculate the answer
                answer = getTotal(solutions, total);
            }//end if-else
            
            // Show the answer
            System.out.println("The minimum number of stamps needed is " + 
                    answer);
            // Prompt user for continue
            System.out.print("Another stamp count? If yes enter 1 else 2: ");
            cont = keyboard.getNextInt() == 1;
        }//end while
        
        // Print the solutions lookup table
        for(int i = 1; i < solutions.length; i++){
            // If the value is a 2 digit number print it
            if(solutions[i] > 9){
                System.out.print(solutions[i] + " ");
            }else{ // otherwise print a space then the value
                System.out.print(" " + solutions[i] + " ");
            }
            // After every 10 values, erase the trailing space and print a new
            // line
            if(i % 10 == 0){
                System.out.println("\b");
            }
        }
        // Print a new line.
        System.out.println("");
    }//end main
    
    /**
     * Gets the total number of stamps needed for a postage of <i>total</i>.
     * Precondition: Solutions array must exist and have length longer than <i>total</i>
     * PostCondition: Solutions array will be updated with the result.
     * @param solutions the array containing the lookup table for postage values
     * @param total the postage value that the you are calculating.
     * @return the total number of stamps for this postage.
     */
    public static int getTotal(int[] solutions, int total){
        // Assert statements for debugging. Checks preconditions
        assert (solutions != null) : "Solutions array not initialized";
        assert (total < solutions.length) : "Postage value outside of available range";
        
        // Assume that the postage takes no stamps: postage of 0;
        int numOfStamps = 0;
        // Create a variable to store the current total.
        int curTotal = total;
        
        // While there is still postage left...
        while(curTotal > 0){
            // Set the starting position to the current total
            int i = curTotal;
            // Find next single stamp value in the solutions array
            for(; (i > 0) && (solutions[i] != 1); i--){
            }
            // Increase the number of stamps by the amount at that location
            // Should always be 1;
            numOfStamps += solutions[i];
            // Change the current total by subtracting the stamp value just placed
            curTotal -= i;
        }//end while
        
        // Set the number of stamps as a solution in the lookup table
        solutions[total] = numOfStamps;
        return numOfStamps;
    }//end getTotal
}//end class
