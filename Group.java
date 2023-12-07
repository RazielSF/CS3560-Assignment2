import java.util.ArrayList;
import java.util.Arrays;
public class Group implements Entity
{
    private String groupID;
    private ArrayList<Entity> groupMembers = new ArrayList<Entity>();

    //Added for Assignment 3
    private long creationTime;

    public Group(String id)
    {
        this.groupID = id;
    }

    public String toString()
    {
        return groupID;
    }

    @Override
    public String getId() 
    {
        return groupID;
    }

    @Override
    public void setId(String id) 
    {
        this.groupID = id;
    }

    public ArrayList<Entity> getEntities()
    {
        return groupMembers;
    }

    public Entity getEntity(int x)
    {
        
        return groupMembers.get(x);
    }

    public int findEntityIndex(Object object)
    {
        int index = groupMembers.indexOf(object);
        
        return index;

    }

    public void addEntity(Entity x)
    {
        this.groupMembers.add(x);
    }

    //Visitor
    @Override
    public void accept(EntityVisitor visitor) 
    {
        visitor.visitGroup(this);

        //visitor.visitGroup(this);
        for(Entity e : groupMembers)
        {
            e.accept(visitor);
        }
    }

    //Added for Assignment 3
    public long getCreationTime()
    {
        return creationTime;
    }
}
