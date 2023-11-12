import java.util.ArrayList;
import java.util.Arrays;
public abstract class Subject 
{
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer)
    {
        observers.add(observer);
    }

    public void detach(Observer observer)
    {
        observers.remove(observer);
    }

    public void notifyObservers()
    {
        for(Observer ob : observers)
        {
            ob.update(this);
        }
    }
}
