package lists;

import interfaces.DequeInterface;
import java.util.ArrayList;
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
public class RegisterLineQueue implements DequeInterface<Customer>{
    
    private final ArrayList<Customer> line;
    
    public RegisterLineQueue(){
        line = new ArrayList<>();
    }
    
    public RegisterLineQueue(int initialCapacity){
        line = new ArrayList<>(initialCapacity);
    }

    @Override
    public void addToBack(Customer newEntry) {
        line.add(newEntry);
    }

    @Override
    public Customer removeFront() {
        Customer front = null;
        if(!isEmpty()){
            front = line.get(0);
            line.remove(0);
        }
        return front;
    }

    @Override
    public Customer getFront() {
        throw new UnsupportedOperationException("Method not supported.");
    }

    @Override
    public boolean isEmpty() {
        return line.isEmpty();
    }

    @Override
    public void clear() {
        line.clear();
    }
    
    public int getLineLength(){
        return line.size();
    }

}
