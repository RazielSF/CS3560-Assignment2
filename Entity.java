//Entities will consist of users and groups
public interface Entity 
{
    public String getId();

    public void setId(String id);

    abstract public void accept(EntityVisitor visitor);
}
