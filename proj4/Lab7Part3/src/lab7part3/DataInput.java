package lab7part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
* A class used for data input 
* @author Quintin Oliver
*/
public class DataInput {
    // Instance variables
    private Scanner input; // Holds the scanner object to simplify input calls
    
    //Constructors
    
    /**
     * Creates a new default Scanner object using System.in.
     */
    public DataInput(){
        input = new Scanner(System.in); // Create a new Scanner object.
    }//end default constructor.
    
    /**
     * Creates a new Scanner object from a file.
     * @param file The file to be read from.
     * @throws FileNotFoundException Thrown if the file is not found.
     */
    public DataInput(File file) throws FileNotFoundException{
        input = new Scanner(file); // Create a new Scanner object.
    }//end File argument constructor.
    
    /**
     * Allows the user to change the input source.
     * @param input The Scanner object to change the input source to.
     */
    public void setInput(Scanner input){
        this.input = input; // Save the new Scanner object.
    }//end setInput
    
    /**
     * Returns the next line from the input buffer (Scanner object).
     * @return The next line from the input.
     */
    public String getNextLine(){
        // Create a variable for testing.
        boolean success = false;
        // Create a blank string to return (in case of error).
        String result = "";
        
        // Repeatedly try to get the next line of the input if there is an error
        do{
            // Try to get the next line.
            try{
                result = this.input.nextLine();
                success = true; // Set the testing variable to true (indicates
                                // correct input)
            }catch(NoSuchElementException nse){
                // If the error is caught, there was nothing in the buffer
                // Tell the user and prompt for re-entry.
                System.out.println("There is nothing in the input buffer.");
                System.out.print("Please try again: ");
                success = false;
            }
        }while(!success);
        // Return the user entered string.
        return result;
    }//end getNextLine
    
    /**
     * Returns the next item in the buffer.
     * @return The next item in the input buffer.
     */
    public String getNext(){
        // Create a variable for testing.
        boolean success = false;
        // Create a blank string to return (in case of error).
        String result = "";
        // Repeatedly try to get the next item of the input if there is an error
        do{
            // Try to get the next item.
            try{
                result = this.input.next();
                success = true; // Set the testing variable to true.
            }catch(NoSuchElementException nse){
                // If the error is caught, the buffer was empty. Re-prompt user.
                System.out.println("There is nothing in the input buffer.");
                System.out.print("Please try again: ");
            }
        }while(!success);
        // Return the resulting string.
        return result;
    }//end getNext.
    
    
    /**
     * Returns the next int in the input buffer.
     * @return The next int from the input.
     */
    public int getNextInt(){
        // Create an int variable to return to the user.
        int num = 0;
        // Create a variable for testing.
        boolean success = false;
        // Repeatedly try to get the next int.
        do{
            // Try to get the next int.
            try{
                num =  this.input.nextInt();
                success = true; // Set the testing variable to true.
            }catch(NoSuchElementException e){
                // If the error was caught, the next item was not an int.
                System.out.println("The input was not an integer value.");
                // Clear the input line to reprompt for more input.
                input.nextLine();
                System.out.print("Please try again: ");
                success = false;
            }
        }while(!success);
        // Return the int to the user.
        return num;
    }//end getNextInt
    
    /**
     * Returns the next double from the input
     * @return The next double on the input line.
     */
    public double getNextDouble(){
        // Create a double variable to return to the user.
        double num = 0.0;
        // Create a testing variable
        boolean success = false;
        // Repeatedly try to get the next double from the input.
        do{
            // Try to get the next double.
            try{
                num = input.nextDouble();
                success = true; // Set success to true.
            }catch(NoSuchElementException e){
                // If the error was caught, the next item was not an acceptable 
                // double value.
                System.out.println("The input was not an decimal value.");
                // Clear the line and reprompt for input.
                input.nextLine();
                System.out.print("Please try again: ");
                success = false;
            }
        }while(!success);
        // Return the next double value.
        return num;
    }//end getNextDouble
    
    /**
     * Returns the next boolean value from the input.
     * @return The next boolean value from the input.
     */
    public boolean getNextBool(){
        // Create a new variable to store the boolean input.
        boolean bool = false;
        // Create a new variable for testing.
        boolean success = false;
        // Repeatedly try to get the next boolean.
        do{
            // Try to get the next boolean value.
            try{
                bool = input.nextBoolean();
                success = true; // Set the testing variable to true.
            }catch(NoSuchElementException e){
                // If the error is caught, the next item was not boolean.
                System.out.println("The input was not an decimal value.");
                input.nextLine(); // Clear the line and repromt.
                System.out.print("Please try again: ");
                success = false;
            }
        }while(!success);
        // return the next boolean.
        return bool;
    }//end getNextBool
}//end class