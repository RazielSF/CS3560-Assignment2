import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class UserPanel implements ActionListener
{
    // UI Elements
    private JFrame frame;
    
    private JTextField followField;
    private JTextField tweetField;

    private JButton followButton;
    private JButton tweetButton;    

    private JList currentFollowing;
    private JList newsFeed;

    private User currentUser;

    private String newFollowerID;
    private String newTweetText;

    private Font textFont = new Font("Arial", Font.BOLD, 20);

    //Holds a list of all existing users
    private ArrayList<User> userList;

    UserPanel(User u, ArrayList<User> uList)
    {
        //Setting User
        this.currentUser = u;
        this.userList = uList;

        //UI Java Swing elements
        //Entire JFrame
        frame = new JFrame(u.toString() + "'s Panel");
        frame.setSize(800, 700);
        frame.setLayout(null);
        //frame.getContentPane().setBackground(Color.GRAY);

        //Add follow/tweet Text Fields
        followField = new JTextField();
        followField.setBounds(25, 25, 300, 50);
        followField.setFont(textFont);

        tweetField = new JTextField();
        tweetField.setBounds(25, 350, 500, 50);
        tweetField.setFont(textFont);

        //Add follow/tweet Buttons
        followButton = new JButton("Follow User");
        followButton.setBounds(400, 25, 300, 50);
        followButton.setFont(textFont);
        followButton.setFocusable(false);
        followButton.addActionListener(this);

        tweetButton = new JButton("Post Tweet");
        tweetButton.setBounds(550, 350, 200, 50);
        tweetButton.setFont(textFont);
        tweetButton.setFocusable(false);
        tweetButton.addActionListener(this);

        //Add List Views
        currentFollowing = new JList<>(currentUser.getFollowersListFeed().getFollowers());
        currentFollowing.setBounds(25, 100, 700, 200);
        currentFollowing.setFont(textFont);
        currentFollowing.setFocusable(false); 

        newsFeed = new JList<>(currentUser.getLiveUserFeed().getFeed());
        newsFeed.setBounds(25, 425, 700, 200);
        newsFeed.setFont(textFont);
        newsFeed.setFocusable(false);

        //Add all to frame
        frame.add(followField);
        frame.add(tweetField);

        frame.add(followButton);
        frame.add(tweetButton);

        frame.add(currentFollowing);
        frame.add(newsFeed);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == followButton)
        {
            //Gets the user ID
            newFollowerID = followField.getText();
            followField.setText("");

            int index = 0;

            for(int x = 0; x < userList.size(); x++)
            {
                if(newFollowerID.equals(userList.get(x).toString()))
                {
                    index = x;
                }
            }

            //Adds current user to new user's followers list, and adds the new user to following list of current user.
            userList.get(index).addToFollowers(currentUser);
            currentUser.addToFollowing(userList.get(index));

        }


        if(e.getSource() == tweetButton)
        {
            //Gets the tweet string
            newTweetText = tweetField.getText();
            tweetField.setText("");

            Tweet newTweet = new Tweet(newTweetText, currentUser);

            currentUser.makeTweet(newTweet);

            //Update Followers' Feeds
            ArrayList<User> needUpdate = currentUser.getFollowers();
            for(int x = 0; x < needUpdate.size(); x++)
            {
                needUpdate.get(x).addToFeed(newTweet);
            }
        }
    }
}
