
package lab7part2;

import java.util.ArrayList;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * Uses a greedy Algorithm to find a set of US coins to make change for a user
 * given amount of currency.
 */
public class Lab7Part2 {

    public static void main(String[] args) {
        // Create an array that stores the values of US coins
        int[] usCoins = {1, 5, 10, 25, 50, 100};
        
        // Create an object for keyboard input
        DataInput keyboard = new DataInput();
        
        // Set the total equal to zero and create an ArrayList that will store
        // the answer
        int total = 0;
        ArrayList<Integer> solution = new ArrayList<>();
        
        // Prompt the user for the amount
        System.out.print("Please enter the amount to make change for: ");
        total = keyboard.getNextInt();
        
        // While there is still change left from the total
        while(total > 0){
            // Subtract the highest coin value from the total and add it to the
            // solution ArrayList
            if(total >= usCoins[5]){ // 100 coin
                total -= usCoins[5];
                solution.add(usCoins[5]);
            }else if(total >= usCoins[4]){ // 50 coin
                total -= usCoins[4];
                solution.add(usCoins[4]);
            }else if(total >= usCoins[3]){ // 25 coin
                total -= usCoins[3];
                solution.add(usCoins[3]);
            }else if(total >= usCoins[2]){ //10coin
                total -= usCoins[2];
                solution.add(usCoins[2]);
            }else if(total >= usCoins[1]){ //5 coin
                total -= usCoins[1];
                solution.add(usCoins[1]);
            }else{                         //1 coin
                total -= usCoins[0];
                solution.add(usCoins[0]);
            }//end if-else
        }//end while
        
        // Output the result
        System.out.println("Change is made of " + solution.size() + " coins:");
        for(Integer coin : solution){
            System.out.print(coin + " ");
        }
        // Erase trailing space and go to new line
        System.out.println("\b");
    }
    
}//end class
