package store_objects;

import lists.RegisterLineQueue;


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
    private double timePerItem;
    private double timePerPayment;
    private int registerNumber;
    private Clock clock;
    private RegisterLineQueue customers;
    private int longestLine;
    private double lastLeaveTime;
    
    
    public Register(double timePerItem, double timePerPayment, int registerNumber){
        this.timePerItem = timePerItem;
        this.timePerPayment = timePerPayment;
        this.registerNumber = registerNumber;
        this.clock = new Clock();
        this.customers = new RegisterLineQueue();
        this.longestLine = 0;
        this.lastLeaveTime = 0;
    }
    
    public void addCustomer(Customer newCustomer){
        customers.addToBack(newCustomer);
        if(getLineLength() > this.longestLine){
            this.longestLine = getLineLength();
        }
    }
    
    public double checkout(Customer customer){
        checkoutCustomer(customer);
        processPayment();
        return clock.time();
    }//end checkout

    private void processPayment() {
        clock.time(clock.time() + timePerPayment);
        lastLeaveTime = clock.time();
    }//end processPayment

    private void checkoutCustomer(Customer customer) {
        double waitTime = 0;
        waitTime = lastLeaveTime - (customer.getArrivalTime() + customer.getCheckoutTime());
        if(waitTime < 0){
            waitTime = 0;
        }else{
            waitTime = waitTime;
        }
        clock.time(clock.time() + customer.getNumOfItems() * timePerItem + waitTime);
    }//end checkoutCustomer
    
    public int getRegisterNumber(){
        return this.registerNumber;
    }
    
    public int getLineLength(){
        return customers.getLineLength();
    }
    
    public Customer getNextCustomer(){
        int l = getLineLength();
        Customer cust = customers.removeFront();
        assert l - 1 == getLineLength();
        return cust;
    }
    
    public int getLongestLineLength(){
        return this.longestLine;
    }
    
}//end class
