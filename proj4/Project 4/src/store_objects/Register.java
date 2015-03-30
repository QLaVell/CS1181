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
    int registerNumber;
    Clock clock;
    
    public Register(double timePerItem, double timePerPayment, int registerNumber){
        this.timePerItem = timePerItem;
        this.timePerPayment = timePerPayment;
        this.registerNumber = registerNumber;
        clock = new Clock();
    }
    
    public double checkout(int numOfItems){
        checkoutCustomer(numOfItems);
        processPayment();
        return clock.time();
    }//end checkout

    private void processPayment() {
        clock.time(clock.time() + timePerPayment);
    }//end processPayment

    private void checkoutCustomer(int numOfItems) {
        clock.time(clock.time() + numOfItems * timePerItem);
    }//end checkoutCustomer
    
    public int getRegisterNumber(){
        return this.registerNumber;
    }
    
}//end class
