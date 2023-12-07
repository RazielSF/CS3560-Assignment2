import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
    
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Admin implements ActionListener, TreeSelectionListener
{
    private static Admin instance = null;

    private JFrame frame;

    private JTextField addUserField;
    private JTextField addGroupField;
    private JTextField showButtonsField;

    private JButton addUserButton;
    private JButton addGroupButton;
    private JButton userViewButton;

    private JButton showUserTotalButton;
    private JButton showGroupTotalButton;
    private JButton showMsgTotalButton;
    private JButton showPositiveButton;

    //Added for assignment 3
    private JButton checkValidationButton;
    private JButton findLastUpdatedUserButton;
    private JTextField validationField;
    private JTextField lastUpdatedField;

    private JTree treeView;

    private Font textFont = new Font("Arial", Font.BOLD, 20);
    private Font textFont2 = new Font("Arial", Font.BOLD, 16);

    private String newUserID;
    private String newGroupID;

    private DefaultMutableTreeNode newUserNode = null;
    private DefaultMutableTreeNode newGroupNode = null;

    private DefaultMutableTreeNode currentGroup;

    private DefaultMutableTreeNode rootNode;

    private DefaultTreeModel treeModel;

    //The root group that holds everything
    private Group rootGroup = new Group("Root");

    //The list of groups and users that exist
    private ArrayList<Group> groupList = new ArrayList<Group>();
    ArrayList<User> userList = new ArrayList<User>();

    protected Admin() 
    {
        //Entire Frame
        frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(null);

        //Add User/Group/Show Text Fields
        addUserField = new JTextField();
        addUserField.setBounds(450, 25, 200, 50);
        addUserField.setFont(textFont);

        addGroupField = new JTextField();
        addGroupField.setBounds(450, 100, 200, 50);
        addGroupField.setFont(textFont);

        showButtonsField = new JTextField("Press a 'Show Button'");
        showButtonsField.setBounds(450, 425, 425, 50);
        showButtonsField.setFont(textFont);

        //Add User/Group Buttons
        addUserButton = new JButton("Add User");
        addUserButton.setBounds(675, 25, 200, 50);
        addUserButton.setFont(textFont);
        addUserButton.setFocusable(false);
        addUserButton.addActionListener(this);

        addGroupButton = new JButton("Add Group");
        addGroupButton.setBounds(675, 100, 200, 50);
        addGroupButton.setFont(textFont);
        addGroupButton.setFocusable(false);
        addGroupButton.addActionListener(this);

        //Add User View Button
        userViewButton = new JButton("Open User View");
        userViewButton.setBounds(450, 175, 425, 50);
        userViewButton.setFont(textFont);
        userViewButton.setFocusable(false);
        userViewButton.addActionListener(this);

        //Add "Show" Buttons
        showUserTotalButton = new JButton("Show User Total");
        showUserTotalButton.setBounds(450, 500, 200, 50);
        showUserTotalButton.setFont(textFont2);
        showUserTotalButton.setFocusable(false);
        showUserTotalButton.addActionListener(this);

        showGroupTotalButton = new JButton("Show Group Total");
        showGroupTotalButton.setBounds(675, 500, 200, 50);
        showGroupTotalButton.setFont(textFont2);
        showGroupTotalButton.setFocusable(false);
        showGroupTotalButton.addActionListener(this);
        
        showMsgTotalButton = new JButton("Show Messages Total");
        showMsgTotalButton.setBounds(450, 575, 200, 50);
        showMsgTotalButton.setFont(textFont2);
        showMsgTotalButton.setFocusable(false);
        showMsgTotalButton.addActionListener(this);

        showPositiveButton = new JButton("Show Positive %");
        showPositiveButton.setBounds(675, 575, 200, 50);
        showPositiveButton.setFont(textFont2);
        showPositiveButton.setFocusable(false);
        showPositiveButton.addActionListener(this);

        //New Buttons for Assignment 3
        checkValidationButton = new JButton("Check Validation");
        checkValidationButton.setBounds(450, 325, 200, 50);
        checkValidationButton.setFont(textFont2);
        checkValidationButton.setFocusable(false);
        checkValidationButton.addActionListener(this);

        findLastUpdatedUserButton = new JButton("Find Last Updated User");
        findLastUpdatedUserButton.setBounds(675, 325, 200, 50);
        findLastUpdatedUserButton.setFont(textFont2);
        findLastUpdatedUserButton.setFocusable(false);
        findLastUpdatedUserButton.addActionListener(this);

        //New Fields for Assignment 3
        validationField = new JTextField("Validation Field");
        validationField.setBounds(450, 250, 200, 50);
        validationField.setFont(textFont);

        lastUpdatedField = new JTextField("Last Updated Field");
        lastUpdatedField.setBounds(675, 250, 200, 50);
        lastUpdatedField.setFont(textFont);

        //Adds the root group as the root node for the tree
        groupList.add(rootGroup);
        rootNode = new DefaultMutableTreeNode(rootGroup);
        treeModel = new DefaultTreeModel(rootNode);
        treeModel.addTreeModelListener(new MyTreeModelListener());


        //Adds the Tree
        treeView = new JTree(treeModel);
        treeView.setEditable(true);
        treeView.setBounds(25, 25, 350, 600);
        treeView.setFont(textFont2);
        treeView.setFocusable(false);
        treeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeView.setShowsRootHandles(true);
        treeView.addTreeSelectionListener(this);

        //Add all to frame
        frame.add(addUserField);
        frame.add(addGroupField);
        frame.add(showButtonsField);

        frame.add(addUserButton);
        frame.add(addGroupButton);
        frame.add(userViewButton);

        frame.add(showUserTotalButton);
        frame.add(showGroupTotalButton);
        frame.add(showMsgTotalButton);
        frame.add(showPositiveButton);

        //Added for assignment 3
        frame.add(checkValidationButton);
        frame.add(findLastUpdatedUserButton);
        frame.add(lastUpdatedField);
        frame.add(validationField);

        frame.add(treeView);

        frame.setVisible(true);
    }

    //Singleton
    public static Admin getInstance()
    {
        if(instance == null)
        {
            instance = new Admin();
        }

        return instance;
    }

    //Tree Listener, used to automatically update the tree?
    @Override
    public void valueChanged(TreeSelectionEvent e)
    {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();

        if (node == null)
        {
            return;
        }

        Object nodeInfo = node.getUserObject();

        if (node.isLeaf())
        {
            
        } 

        else
        {
            currentGroup = node;
        }
    } 


    //Button Actions
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Adding Users
        if(e.getSource() == addUserButton)
        {
            //Creates a new user and node
            newUserID = addUserField.getText();
            addUserField.setText("");

            User newUser = new User(newUserID);

            userList.add(newUser);

            newUserNode = new DefaultMutableTreeNode(newUser);

            //Finds currently selectred Tree Node
            DefaultMutableTreeNode parentNode = null;
            TreePath parentPath = treeView.getSelectionPath();

            if(parentPath == null)
            {
                parentNode = rootNode;
            }

            else
            {
                parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
            }

            //Only adds a user if selected node is a group (Does not add user if selected node is not a group)
            if(parentNode.getUserObject().getClass().equals(rootGroup.getClass()))
            {
                DefaultMutableTreeNode childNode = newUserNode;

                treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
                treeView.scrollPathToVisible(new TreePath(childNode.getPath()));

                //Adds user directly to the root node if selected node is not another group
                if (parentNode == rootNode)
                {
                    rootGroup.addEntity(newUser);
                }

                //Adds user to a group (It will still be part of the root node, but it'll be inside a group in the root list)
                else
                {
                    int index = groupList.indexOf(parentNode.getUserObject());

                    groupList.get(index).addEntity(newUser);

                }
            } 

            //Does nothing if selected node is not a group node
            else
            {

            }
        }

        //Adding Groups
        if(e.getSource() == addGroupButton)
        {
            newGroupID = addGroupField.getText();
            addGroupField.setText("");

            Group newGroup = new Group(newGroupID);

            groupList.add(newGroup);

            newGroupNode = new DefaultMutableTreeNode(newGroup);

            DefaultMutableTreeNode parentNode = null;
            TreePath parentPath = treeView.getSelectionPath();

            if(parentPath == null)
            {
                parentNode = rootNode;
            }

            else
            {
                parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
            }

            //Only adds a group if selected node is a group (Does not add user if selected node is not a group)
            if(parentNode.getUserObject().getClass().equals(rootGroup.getClass()))
            {
                DefaultMutableTreeNode childNode = newGroupNode;

                treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
                treeView.scrollPathToVisible(new TreePath(childNode.getPath()));

                //Adds group directly to the root node if selected node is not another group
                if (parentNode == rootNode)
                {
                    rootGroup.addEntity(newGroup);
                }

                //Adds group to an existing group (It will still be part of the root node, but it'll be inside a group in the root list)
                else
                {

                    int index = groupList.indexOf(parentNode.getUserObject());

                    groupList.get(index).addEntity(newGroup);

                }
            } 

            //Does nothing if selected node is not a group node
            else
            {

            }
        }

        //Open User Panel
        if(e.getSource() == userViewButton)
        {
            DefaultMutableTreeNode currentNode = null;
            TreePath currentPath = treeView.getSelectionPath();

            //If no option on tree is selected, do nothing (Tree selection must be a user in order to open user view)
            if(currentPath == null)
            {
                
            }

            //Continue if there is something currently selected on tree
            else
            {
                currentNode = (DefaultMutableTreeNode) (currentPath.getLastPathComponent());

                DefaultMutableTreeNode parentNode =  null;
                parentNode = (DefaultMutableTreeNode) currentNode.getParent();

                //Only continue if the selected item is a user, not a group
                //It specifically checks of the object is a group, if it is not, it continues
                if(currentNode.getUserObject().getClass().equals(rootGroup.getClass()) == false)
                {
                    //Finds the index of the user directly 
                    int index1 = groupList.indexOf(parentNode.getUserObject());

                    int index2 = groupList.get(index1).findEntityIndex(currentNode.getUserObject());

                    User y = (User) groupList.get(index1).getEntity(index2);
                    
                    UserPanel uPanel = new UserPanel(y, userList);
                }

                //Does nothing if selection is not a user
                else
                {

                }
            }
        }

        //Show Buttons
        //Group Total
        if(e.getSource() == showGroupTotalButton)
        {
            VisitorCounter v = new VisitorCounter();
            rootGroup.accept(v);
            showButtonsField.setText("Total Number of Groups: " + v.getGroupCounter());

        }

        //User Total
        if(e.getSource() == showUserTotalButton)
        {
            VisitorCounter v = new VisitorCounter();
            rootGroup.accept(v);
            showButtonsField.setText("Total Number of Users: " + v.getUserCounter());
        }

        //Message Total
        if(e.getSource() == showMsgTotalButton)
        {
            MessageCounterVisitor v = new MessageCounterVisitor();
            rootGroup.accept(v);
            showButtonsField.setText("Total Number of Messages: " + v.getMessageCounter());
        }

        //Positive Total
        if(e.getSource() == showPositiveButton)
        {
            //Counts total messages
            MessageCounterVisitor v = new MessageCounterVisitor();
            rootGroup.accept(v);
            int totalMsg = v.getMessageCounter();

            //Counts total positive messages
            PositivityVisitor v2 = new PositivityVisitor();
            rootGroup.accept(v2);
            int totalPositive = v2.getPositiveCounter();

            //Return 0 if there are zero messages (Don't want to divide by zero)
            if(totalMsg == 0)
            {
                showButtonsField.setText("Positive Message Percentage: 0.0%");
            }

            else
            {
                double positivePercent = (double) totalPositive / (double) totalMsg;
                positivePercent = positivePercent * 100;
                String percentage = Double.toString(positivePercent).substring(0, 4);
                showButtonsField.setText("Positive Message Percentage: " + percentage + "%");
            }

        }

        //New Buttons for Assignment 3
        if((e.getSource() == checkValidationButton))
        {
            //Gets the names of all users and groups
            ValidVisitor v = new ValidVisitor();
            rootGroup.accept(v);

            ArrayList<String> names = new ArrayList<>();

            names = v.getNamesList();

            boolean valid = true;
        
            //Checks if IDs are valid
            for(int y = 0; y < names.size(); y++)
            {
                //Checks if ID has a space
                if(names.get(y).contains(" "))
                {
                    valid = false;
                }

                //Checks if ID is unique
                for(int z = 0; z < names.size(); z++)
                {
                    if(z == y)
                    {

                    }

                    else
                    {
                        if(names.get(y).equals(names.get(z)))
                        {
                            valid = false;
                        }
                    }
                }

            }

            if(valid == true)
            {
                validationField.setText("IDs are Valid");
            }

            else
            {
                validationField.setText("IDs are not Valid");
            }

        }

        if(e.getSource() == findLastUpdatedUserButton)
        {
            //Gets all users into a list
            UpdatedTimeVisitor u = new UpdatedTimeVisitor();
            rootGroup.accept(u);

            ArrayList<User> users = new ArrayList<>();

            users = u.getUsers();

            //If no users exist
            if(users.size() == 0)
            {
                lastUpdatedField.setText("No Users Available");
            }

            //Checks all users latest updated time and returns the user with the largest value
            else
            {
                long time;
                long largest = 0;
                int latestIndex = 0;

                for(int x = 0; x < users.size(); x++)
                {
                    time = users.get(x).getLastUpdatedTime();

                    if(time > largest)
                    {
                    largest = time;
                    latestIndex = x;
                    }
                }

                String lastUpdatedName = users.get(latestIndex).toString();


                lastUpdatedField.setText(lastUpdatedName);
            }
            

        }

    }
}
