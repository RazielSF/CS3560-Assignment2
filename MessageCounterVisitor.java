import java.util.ArrayList;
public class MessageCounterVisitor implements EntityVisitor
{
    private int messageCounter = 0;

    @Override
    public void visitGroup(Group g) 
    {

    }

    @Override
    public void visitUser(User u) 
    {
        int x = u.getUserTweets().size();

        setMessageCounter(getMessageCounter() + x);
    }

    public void setMessageCounter(int count)
    {
        this.messageCounter = count;
    }

    public int getMessageCounter()
    {
        return messageCounter;
    }
}
