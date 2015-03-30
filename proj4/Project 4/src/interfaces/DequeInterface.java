package interfaces;


/*
 * Quintin Oliver
 * Section 09
 * TA: Jacob Ross
 * Lecture: Michael Ondrasek
 */

/**
 * [Describe Interface here]
 */
public interface DequeInterface<T> {
    public void addToBack(T newEntry);
    public T removeFront();
    public T getFront();
    public boolean isEmpty();
    public void clear();
}
