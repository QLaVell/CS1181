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
public class Event implements Comparable<Event>{
    private EventType eventType;
    private double eventTime;
    private Object[] eventParameter;
    
    public Event(EventType eventType, double eventTime, Object[] eventParameter){
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.eventParameter = eventParameter;
    }

    public EventType getEventType() {
        return eventType;
    }

    public double getEventTime() {
        return eventTime;
    }

    public Object[] getEventParameter() {
        return eventParameter;
    }

    @Override
    public int compareTo(Event event) {
        int result = 0;
        if(this.eventTime  > event.getEventTime()){
            result = 1;
        }else if(this.eventTime < event.getEventTime()){
            result = -1;
        }
        
        return result;
    }
    
}
