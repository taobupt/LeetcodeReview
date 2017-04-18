package _04_09;

import common.ListNode;
import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tao on 4/14/2017.
 */
public class ThreeHundredTwentysixToThirtyFiveTest {
    ThreeHundredTwentysixToThirtyFive THTTT=null;
    @Before
    public void setup(){
        THTTT=new ThreeHundredTwentysixToThirtyFive();
    }

    @Test
    public void testPowerOfThree()throws Exception{
        System.out.println(THTTT.isPowerOfThreeMath(243));
    }

    @Test
    public void testOddEvenList(){
        ListNode node=new ListNode(1);
        node.next=new ListNode(2);
        node.next.next=new ListNode(3);
        node.next.next.next=new ListNode(4);
        ListNode resnode=THTTT.oddEvenListSaveSpace(node);
        while(resnode!=null){
            System.out.println(resnode.val);
            resnode=resnode.next;
        }
    }

    @Test
    public void testLongestIncreasingPath(){
        int [][]matrix={{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        System.out.println(THTTT.longestIncreasingPath(matrix));
    }

    @Test
    public void testRange(){
        int []nums={2147483647,-2147483648,-1,0};
        System.out.println(THTTT.countRangeSum(nums,-1,0));
    }

    @Test
    public void testLargest(){
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.left.left=new TreeNode(2);
        root.left.left.right=new TreeNode(4);
        System.out.println(THTTT.largestBSTSubtree(root));
    }
}
