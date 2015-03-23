package store_objects;


/*
* Quintin Oliver
* Section 09
* TA: Jacob Ross
* Lecture: Michael Ondrasek
*/

/**
 * This class manages the simulation clock.
 */
public class Clock {
    public static double now;
    
    /**
     * Default constructor initializes the static variable now
     */
    public Clock(){
        now = 0.0;
    }//end constructor
    
    /**
     * Returns the current simulation clock time.
     * @return the current simulation clock time.
     */
    public double time(){
        return now;
    }//end time
    
    /**
     * Sets the current time for the simulation clock.
     * @param newTime the new time to set the clock to
     */
    public void time(double newTime){
        Clock.now = newTime;
    }//end time
}
