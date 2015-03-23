package store_objects;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * [Describe class here]
 */
public class Register{
    double timePerItem;
    double timePerPayment;
    Clock clock;
    
    public Register(double timePerItem, double timePerPayment){
        this.timePerItem = timePerItem;
        this.timePerPayment = timePerPayment;
        clock = new Clock();
    }
    
    public void checkout(int numOfItems){
        checkoutCustomer(numOfItems);
        processPayment();
    }//end checkout

    private void processPayment() {
        clock.time(clock.time() + timePerPayment);
    }//end processPayment

    private void checkoutCustomer(int numOfItems) {
        clock.time(clock.time() + numOfItems * timePerItem);
    }//end checkoutCustomer
    
}//end class
