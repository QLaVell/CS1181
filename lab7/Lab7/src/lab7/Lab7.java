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
        // Create an object for user input
        DataInput keyboard = new DataInput();
        // Create an ArrayList to hold the values of the coins
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
        getSolutions(partialSolution, coins, total, coins.size() - 1);
        
        // If there is a solution print it, otherwise state that there are no
        // solutions.
        if(solutions.size() > 0){
            for(int coin : solutions.get(0)){
                System.out.print(coin + " ");
            }
            System.out.println("");
        }else{
            System.out.println("There are no possible solutions.");
        }
    }//end main
    
    /**
     * Finds all possible solutions that will make change from <i>total</i>.
     * Precondition: Coins must contain at least 1 value.
     * Postcondition: <i>solution</i> will contain all possible solutions.
     * @param partialSolution the current working solution for change.
     * @param coins the coins available to make change
     * @param total the total amount to be reached
     * @param startPos the position in the coin ArrayList
     */
    public static void getSolutions(ArrayList<Integer> partialSolution, 
            ArrayList<Integer> coins, int total, int startPos){
        // Get current partial solution totale
        int partialTotal = getTotal(partialSolution);
        // Case partial solution = total
        if(partialTotal == total){
            solutions.add(partialSolution);
        }else{
            // Starting after the last coin added...
            for(int i = startPos; i >= 0; i--){
                // Get the current coin
                int coin = coins.get(i);
                // Get a copy of the current partial solution
                ArrayList<Integer> temp = getCopy(partialSolution);
                // Get a copy of the partial total to manipulate
                int tempTotal = partialTotal;
                
                // Add coins until it will go over the total
                while(tempTotal + coin <= total){
                    tempTotal += coin;
                    temp.add(coin);
                }
                // Test the current partial solution to see if it works
                getSolutions(temp, coins, total, i - 1);
            }//end for
        }//end if-else
    }//end getSolution
    
    /**
     * Calculates the total of the integers in an ArrayList.
     * Precondition: <i>nums</i> must contain Integers.
     * PostCondition: N/A
     * @param nums the ArrayList to calculate the total of.
     * @return the total of all integers in <i>nums</i>
     */
    public static int getTotal(ArrayList<Integer> nums){
        // Assume there's nothing in the ArrayList
        int total = 0;
        // Iterate through the list and add each item to total
        for(int num : nums){
            total += num;
        }//end for
        return total;
    }//end getTotal
    
    /**
     * Returns a copy of the provided ArrayList.
     * Precondition: <i>list</i> must exist.
     * Postcondition: N/A
     * @param list the ArrayList object to be copied
     * @return a deep copy of the provided ArrayList
     */
    public static ArrayList<Integer> getCopy(ArrayList<Integer> list){
        // Create a temporary ArrayList
        ArrayList<Integer> temp = new ArrayList<>();
        // Iterate through the ArrayList and copy each item to the temporary array
        for(int num : list){
            temp.add(num);
        }//end for
        return temp;
    }//end getCopy
}//end class
