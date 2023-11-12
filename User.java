import java.util.ArrayList;
import java.util.Arrays;
public class User implements Entity
{
    private String userID;
    private ArrayList<User> followersList = new ArrayList<User>();
    private ArrayList<User> followingList = new ArrayList<User>();
    private ArrayList<Tweet> userFeed = new ArrayList<Tweet>();
    private ArrayList<Tweet> userTweets = new ArrayList<Tweet>();

    //Observers
    private FollowersListFeed liveFollowers = new FollowersListFeed();
    private FollowersList followersSubject = new FollowersList();

    private LiveUserFeed liveFeed = new LiveUserFeed();
    private UserFeed feedSubject = new UserFeed();

    public User(String id)
    {
        this.userID = id;

        //Observers
        feedSubject.attach(liveFeed);
        feedSubject.setTweets(this.getUserFeed());

        followersSubject.attach(liveFollowers);
        followersSubject.setFollowers(this.getFollowing());
    }

    public User()
    {
        this.userID = null;
    }

    public String toString()
    {
        return userID;
    }

    @Override
    public String getId() 
    {
        return userID;
    }

    @Override
    public void setId(String id) 
    {
        this.userID = id;
    }
    
    //Add to followers or following
    public void addToFollowers(User newFollower)
    {
        this.followersList.add(newFollower);

    }

    public void addToFollowing(User newFollowing)
    {
        this.followingList.add(newFollowing);
        
        for(int x = 0; x < newFollowing.userTweets.size(); x++)
        {
            this.userFeed.add(newFollowing.userTweets.get(x));
        }

        followersSubject.addFollower(newFollowing);
        feedSubject.setTweets(userFeed);
    }

    //Get followers/ings
    public ArrayList<User> getFollowers()
    {
        return followersList;
    }

    public ArrayList<User> getFollowing()
    {
        return followingList;
    }

    //Makes/Adding tweets to user's tweets and feed
    public void makeTweet(String text)
    {
        Tweet t = new Tweet(text, this);
        this.userTweets.add(t);
        this.userFeed.add(t);
        this.feedSubject.setTweets(userFeed);
    }

    public void makeTweet(Tweet t)
    {
        this.userTweets.add(t);
        this.userFeed.add(t);
        this.feedSubject.setTweets(userFeed);
    }

    public void addToFeed(Tweet t)
    {
        this.userFeed.add(t);
        feedSubject.setTweets(userFeed);
    }

    //Displays User's Tweets (Used during testing)
    public void displayUserTweets()
    {
        for(int x = userTweets.size() - 1; x >= 0; x--)
        {
            this.userTweets.get(x).displayText();
        }
    }

    //Get User tweets and feed
    public ArrayList<Tweet> getUserFeed()
    {
        return this.userFeed;
    }

    public ArrayList<Tweet> getUserTweets()
    {
        return this.userTweets;
    }

    //Returns observers
    public LiveUserFeed getLiveUserFeed()
    {
        return this.liveFeed;
    }

    public FollowersListFeed getFollowersListFeed()
    {
        return liveFollowers;
    }

    //Visitor
    @Override
    public void accept(EntityVisitor visitor) 
    {
        visitor.visitUser(this);
    }
}
