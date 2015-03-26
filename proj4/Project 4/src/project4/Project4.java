package project4;

import java.io.File;
import java.io.FileNotFoundException;
import store_objects.Customer;
import store_objects.Register;
import store_objects.Clock;

/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * This Project simulates a grocery store. Using a simulated clock, the program
 * tests the time and number of customers going through the store and checkout
 * lanes.
 */
public class Project4 {

    public static void main(String[] args) {
        try{
            DataInput customerArrivals = new DataInput(new File("arrivals.txt"));

            // Create a clock object to access the time
            Clock clock = new Clock();

            // Create a register
            Register regRegister = new Register(0.05, 2.5);

            // Create a new Customer
            Customer cust = new Customer(3.5, 18, 1);

            clock.time(clock.time() + cust.getArrivalTime());
            System.out.println("First customer arrives. Time is: " + clock.time());
            System.out.println("Customer arriving at register...");
            clock.time(clock.time() + cust.getCheckoutTime());
            System.out.println("Current time is: " + clock.time());
            System.out.println("Checking out a customer");
            regRegister.checkout(cust.getNumOfItems());
            System.out.println("New time is: " + clock.time());
        }catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    
}
