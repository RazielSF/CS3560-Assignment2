import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;

public class FollowersList extends Subject
{
    private DefaultListModel<User> followers = new DefaultListModel<>();

    public void addFollower(User newFollower)
    {
        this.followers.addElement(newFollower);

        notifyObservers();
    }

    public void setFollowers(ArrayList<User> f)
    {
        for(int x = 0; x < f.size(); x++)
        {
            this.followers.add(x, f.get(x));
        }

        notifyObservers();
    }

    public DefaultListModel<User> getFollowers()
    {
        return followers;
    }

}
