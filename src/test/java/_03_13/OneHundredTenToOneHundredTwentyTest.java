package _03_13;

import common.Tree;
import common.TreeLinkNode;
import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/13/17.
 */
public class OneHundredTenToOneHundredTwentyTest {

    OneHundredTenToOneHundredTwenty ot=null;
    @Before
    public void setup(){
        ot=new OneHundredTenToOneHundredTwenty();
    }
    @Test
    public void pathSumII() throws Exception {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(5);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        //root.right.left=new TreeNode(0);
        root.right.right=new TreeNode(6);
//        root.left.left.left=new TreeNode(0);
//        root.left.left.right=new TreeNode(1);
//        root.left.right.left=new TreeNode(-1);
//        root.left.right.right=new TreeNode(0);
//        root.right.left.left=new TreeNode(-1);
//        root.right.left.right=new TreeNode(0);
//        root.right.right.left=new TreeNode(1);
//        root.right.right.right=new TreeNode(0);
//        ot.pathSumII(root,2);
        ot.flattenIterative(root);
        Tree t=new Tree();
        t.preorderByStack(root);
    }

    @Test
    public void testDistinct()throws Exception{
        System.out.println(ot.numDistinct("rabbbit","rabbit"));
    }
    @Test
    public void testConnect()throws Exception{
        TreeLinkNode root=new TreeLinkNode(1);
        root.left=new TreeLinkNode(2);
        root.right=new TreeLinkNode(3);
        root.left.left=new TreeLinkNode(4);
        root.left.right=new TreeLinkNode(5);
        root.right.left=new TreeLinkNode(6);
        root.right.right=new TreeLinkNode(7);
        ot.connectWithoutExtraSpace(root);
    }

}