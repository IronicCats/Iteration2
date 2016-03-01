package Utitlies;

/**
 * Created by Joshua Kegley on 2/29/2016.
 */
public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void alert();
}
