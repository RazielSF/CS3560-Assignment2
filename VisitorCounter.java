import java.util.ArrayList;
//This Visitor Counter can count both groups and users
public class VisitorCounter implements EntityVisitor
{
    private int userCounter = 0;
    private int groupCounter = 0;

    @Override
    public void visitGroup(Group g) 
    {
        setCounterGroup(getGroupCounter() + 1);
    }

    @Override
    public void visitUser(User u) 
    {
        setCounterUser(getUserCounter() + 1);
    }

    public void setCounterUser(int count)
    {
        this.userCounter = count;
    }

    public void setCounterGroup(int count)
    {
        this.groupCounter = count;
    }

    public int getUserCounter()
    {
        return userCounter;
    }

    public int getGroupCounter()
    {
        return groupCounter;
    }
    
}
