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
    private double downTime;
    private double totalWaitTime;
    private int numOfCustomers;
    private boolean idle;
    
    
    public Register(double timePerItem, double timePerPayment, int registerNumber){
        this.timePerItem = timePerItem;
        this.timePerPayment = timePerPayment;
        this.registerNumber = registerNumber;
        this.clock = new Clock();
        this.customers = new RegisterLineQueue();
        this.longestLine = 0;
        this.lastLeaveTime = 0;
        this.downTime = 0;
        this.totalWaitTime = 0;
        this.numOfCustomers = 0;
        this.idle = true;
    }
    
    public void addCustomer(Customer newCustomer){
        customers.addToBack(newCustomer);
        numOfCustomers++;
        idle = false;
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
            this.downTime += Math.abs(waitTime); 
            waitTime = 0;
        }else{
            totalWaitTime += waitTime;
        }
        System.out.println("Customer " + customer.getCustomerNumber() + " waited " + waitTime + " minutes");
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
        if (l == 0){
            idle = true;
        }
        assert l - 1 == getLineLength();
        return cust;
    }
    
    public int getLongestLineLength(){
        return this.longestLine;
    }
    
    public double getDownTime(){
        return this.downTime;
    }
    
    public double getTotalWaitTime(){
        return this.totalWaitTime;
    }
    
    public int getTotalCustomers(){
        return numOfCustomers;
    }
    
}//end class
