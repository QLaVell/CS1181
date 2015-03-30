package store_objects;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * A customer object that holds arrival time, number of items, time spent per 
 * item.
 */
public class Customer implements Comparable<Customer>{
    double arrivalTime;
    int numOfItems;
    double timePerItem;
    int customerNumber;
    
    public Customer(double arrivalTime, int numOfItems, double timePerItem){
        this.arrivalTime = arrivalTime;
        this.numOfItems = numOfItems;
        this.timePerItem = timePerItem;
    }//end constructor
    
    public double getCheckoutTime(){
        return (numOfItems * timePerItem);
    }//end getCheckoutTime
    
    public double getArrivalTime(){
        return this.arrivalTime;
    }//end getArrivalTime
    
    public int getNumOfItems(){
        return this.numOfItems;
    }//end getNumOfItems
    
    public double getTimePerItem(){
        return this.timePerItem;
    }//end getTimePerItem
    
    public void setCustomerNumber(int number){
        this.customerNumber = number;
    }
    
    public int getCustomerNumber(){
        return this.customerNumber;
    }

    @Override
    public int compareTo(Customer cust) {
        int result = 0;
        
        if(this.arrivalTime > cust.getArrivalTime()){
            result = 1;
        }else if(this.arrivalTime < cust.getArrivalTime()){
            result = -1;
        }
        
        return result;
    }//end compareTo
    
}
