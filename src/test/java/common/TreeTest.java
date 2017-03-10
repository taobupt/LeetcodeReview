package common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/9/17.
 */
public class TreeTest {
    @Test
    public void postorderByStack() throws Exception {
        TreeNode node=new TreeNode(1);
        node.left=new TreeNode(2);
        node.right=new TreeNode(3);
        node.left.left=new TreeNode(4);
        node.left.right=new TreeNode(5);
        Tree t= new Tree();
        t.postorderByAnotherStack((node));
    }

}