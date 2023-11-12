import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

public class LiveUserFeed implements Observer
{
    private DefaultListModel<Tweet> userFeed = new DefaultListModel<>();
    private DefaultListModel<Tweet> userFeed2 = new DefaultListModel<>();
    private DefaultListModel<Tweet> userFeedUnsorted = new DefaultListModel<>();

    @Override
    public void update(Subject subject) 
    {
        if(subject instanceof UserFeed)
        {
            this.userFeed2 = (((UserFeed) subject).getTweets());

           addTweets();
        }
    }

    //Adds tweets to an unsorted Feed
    public void addTweets()
    {
        userFeed.clear();
        userFeedUnsorted.clear();

        for(int x = 0; x < userFeed2.size(); x++)
        {
            this.userFeedUnsorted.add(x, (userFeed2.get(x)));
        }

        sortTweets();
    }

    //Sorts Tweets by most recent
    private void sortTweets() 
    {
        userFeed.clear();
        userFeed2.clear();
        while(userFeedUnsorted.size() != 0)
        {
            int y = 0;
            long time = 0;
            long largest = Long.MAX_VALUE;
            int latestIndex = 0;

            for(int x = 0; x < userFeedUnsorted.size(); x++)
            {
                time = userFeedUnsorted.getElementAt(x).getTimeStamp();

                if(time < largest)
                {
                    largest = time;
                    latestIndex = x;
                }
            }
            
            this.userFeed.add(y, userFeedUnsorted.get(latestIndex));
            this.userFeedUnsorted.removeElementAt(latestIndex);
            y++;
        }
    }

    public DefaultListModel<Tweet> getFeed()
    {
        return userFeed;   
    }
    
}
