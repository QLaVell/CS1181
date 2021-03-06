package lists;

import java.util.PriorityQueue;
import store_objects.Customer;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * [Describe class here]
 */
public class CustomerList{
    private PriorityQueue<Customer> list;
    
    public CustomerList(){
        list = new PriorityQueue<>();
    }//end constructor
    
    /**
     * Returns the next customer in the queue
     * @return the next customer in the queue
     */
    public Customer getNextCustomer(){
        Customer nextCustomer = null;
        
        if(!list.isEmpty()){
            nextCustomer = list.poll();
        }
        
        return nextCustomer;
    }//end getNextCustomer
    
    public boolean addNewCustomer(Customer newCustomer){
        return list.add(newCustomer);
    }//end addNewCustomer
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
}
