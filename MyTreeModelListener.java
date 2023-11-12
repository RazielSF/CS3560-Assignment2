import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyTreeModelListener implements TreeModelListener
{

    @Override
    public void treeNodesChanged(TreeModelEvent e) 
    {
        DefaultMutableTreeNode node;

        node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

        try
        {
            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode) (node.getChildAt(index));
        } catch (NullPointerException exc) {}

    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) 
    {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) 
    {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) 
    {

    }
    
}
