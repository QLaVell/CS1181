package lab7;

import java.util.ArrayList;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * Finds a set of coins to make change for a user entered amount of currency
 * using the backtracking algorithm.
 */
public class Lab7 {
    // Create an ArrayList that will hold all of the solutions
    static ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();
    
    public static void main(String[] args) {
        DataInput keyboard = new DataInput();
        ArrayList<Integer> coins = new ArrayList<>();
        int numOfCoins;
        int total;
        ArrayList<Integer> partialSolution = new ArrayList<>();
        
        // Prompt the user for the amount of change
        System.out.print("Enter amount: " );
        total = keyboard.getNextInt();
        
        // Prompt user for the number of coins
        System.out.print("Enter number of coins: ");
        numOfCoins = keyboard.getNextInt();
        
        // Prompt the user for the coin values
        System.out.print("Enter coin values: ");
        for(int i = 0; i < numOfCoins; i++){
            coins.add(keyboard.getNextInt());
        }//end for
        coins.sort(null);
        
        // Get the combinations
        getSolutions(partialSolution, coins, total);
    }//end main
    
    
    public static void getSolutions(ArrayList<Integer> partialSolution, 
            ArrayList<Integer> coins, int total){
        // Get current partial solution totale
        int partialTotal = getTotal(partialSolution);
        // Case partial solution = total
        if(partialTotal == total){
            solutions.add(partialSolution);
        }else{
            int i = coins.size();
        }
    }//end getSolution
    
    public static int getTotal(ArrayList<Integer> nums){
        int total = 0;
        for(int num : nums){
            total += num;
        }
        return total;
    }
}//end class
