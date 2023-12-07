import java.util.ArrayList;

public class UpdatedTimeVisitor implements EntityVisitor
{
    private ArrayList<User> users = new ArrayList<User>();

    @Override
    public void visitGroup(Group g) 
    {
        
    }

    @Override
    public void visitUser(User u) 
    {
        users.add(u);
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }
}
