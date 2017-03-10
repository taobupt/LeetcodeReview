package _03_07;

import common.List;
import common.ListNode;
import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/10/17.
 */
public class NinetyOneToOneHundredTest {

    NinetyOneToOneHundred nh=null;
    @Before
    public void setup(){
        nh=new NinetyOneToOneHundred();
    }


    @Test
    public void recoverTree() throws Exception {
        TreeNode root=new TreeNode(0);
        root.left=new TreeNode(1);
        nh.recoverTree(root);
    }

    @Test
    public void numberOfTree()throws Exception{
        System.out.println(nh.numTrees(4));
    }

    @Test
    public void reverselist()throws Exception{
        List l=new List();
        int []nums={1,2,3,4,5};
        l.printList(nh.reverseBetween(l.createList(nums),2,4));
    }
}