package _03_27;

import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/28/17.
 */
public class TwoHundredTwentyFiveToThirtyFiveTest {


    TwoHundredTwentyFiveToThirtyFive TTFTT=null;
    @Before
    public void setup(){
        TTFTT=new TwoHundredTwentyFiveToThirtyFive();
    }
    @Test
    public void kthSmallest() throws Exception {
        TreeNode node=new TreeNode(5);
        node.left=new TreeNode(3);
        node.left.left=new TreeNode(2);
        node.left.right=new TreeNode(4);
        node.right=new TreeNode(7);
        node.right.left=new TreeNode(6);
        node.right.right=new TreeNode(8);
        TTFTT.kthSmallest(node,2);
    }

}