import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;

public class UserFeed extends Subject
{
    private DefaultListModel<Tweet> feed = new DefaultListModel<>();

    public void addTweet(Tweet newTweet)
    {
        this.feed.addElement(newTweet);

        notifyObservers();
    }

    public void setTweets(ArrayList<Tweet> f)
    {
        for(int x = 0; x < f.size(); x++)
        {
            this.feed.add(x, f.get(x));
        }

        notifyObservers();
    }

    public DefaultListModel<Tweet> getTweets()
    {
        return feed;
    }




}
