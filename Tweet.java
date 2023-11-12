//A tweet contains text and a timestamp
//For purposes of this project, the User's name will be ammended to the start of the tweet as " UserName: 'Tweet Text' "
public class Tweet 
{
    private String tweetText;
    private long timeSent;
    private boolean positive;
    
    public Tweet()
    {
        this.tweetText = "";
        this.timeSent = System.currentTimeMillis();
        this.checkPositivity();
    }

    public Tweet(String text, User u)
    {
        this.tweetText = u.toString() + ": " + text;
        this.timeSent = System.currentTimeMillis();
        this.checkPositivity();
    }

    //Checks for the words "happy", "good", "great", "awesome", or "excellent"
    private void checkPositivity()
    {
        if(this.tweetText.contains("happy") || this.tweetText.contains("good") || this.tweetText.contains("great") || this.tweetText.contains("awesome") || this.tweetText.contains("excellent"))
        {
            this.positive = true;
        }

        else
        {
            this.positive = false;
        }
    }

    public String toString()
    {
        return tweetText;
    }

    public String getText()
    {
        return tweetText;
    }

    public void displayText()
    {
        System.out.println(tweetText);
    }

    public long getTimeStamp()
    {
        return timeSent;
    }

    public boolean getPositivity()
    {
        return positive;
    }
}
