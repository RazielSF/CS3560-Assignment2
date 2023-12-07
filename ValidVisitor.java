import java.util.ArrayList;
public class ValidVisitor implements EntityVisitor
{
    private ArrayList<String> names = new ArrayList<String>();

    @Override
    public void visitGroup(Group g) 
    {
        names.add(g.toString());
    }

    @Override
    public void visitUser(User u) 
    {
        names.add(u.toString());
    }

    public ArrayList<String> getNamesList()
    {
        return names;
    }
}