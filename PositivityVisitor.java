import java.util.ArrayList;
public class PositivityVisitor implements EntityVisitor
{
    private int positiveCounter = 0;

    @Override
    public void visitGroup(Group g) 
    {

    }

    @Override
    public void visitUser(User u) 
    {
        int x = 0;
        for(int y = 0; y< u.getUserTweets().size(); y++)
        {
            if(u.getUserTweets().get(y).getPositivity() == true)
            {
                x += 1;
            }
        }

        setPositiveCounter(getPositiveCounter() + x);
    }

    public void setPositiveCounter(int count)
    {
        this.positiveCounter = count;
    }

    public int getPositiveCounter()
    {
        return positiveCounter;
    }
}
