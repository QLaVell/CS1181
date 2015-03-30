package lists;

import java.util.PriorityQueue;
import store_objects.Event;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * [Describe class here]
 */
public class EventList {
    private PriorityQueue<Event> list;
    
    public EventList(){
        list = new PriorityQueue<>();
    }
    
    /**
     * Returns the next customer in the queue
     * @return the next customer in the queue
     */
    public Event getNextEvent(){
        Event nextEvent = null;
        
        if(!list.isEmpty()){
            nextEvent = list.poll();
        }
        
        return nextEvent;
    }//end getNextCustomer
    
    public boolean addNewEvent(Event newEvent){
        return list.add(newEvent);
    }//end addNewCustomer
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
}
