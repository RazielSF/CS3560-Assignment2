import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

public class FollowersListFeed implements Observer
{
    private DefaultListModel<User> followers = new DefaultListModel<>();
    private DefaultListModel<User> followers2 = new DefaultListModel<>();

    @Override
    public void update(Subject subject) 
    {
        if(subject instanceof FollowersList)
        {
            this.followers2 = (((FollowersList) subject).getFollowers());

           addFollowers();
        }
    }

    public void addFollowers()
    {
        followers.clear();

        for(int x = 0; x < followers2.size(); x++)
        {
            this.followers.add(x, (followers2.get(x)));
        }
    }

    public DefaultListModel<User> getFollowers()
    {
        return followers;   
    }
    
}
