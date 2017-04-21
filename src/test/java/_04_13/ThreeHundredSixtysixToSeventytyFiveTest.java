package _04_13;

import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/20/17.
 */
public class ThreeHundredSixtysixToSeventytyFiveTest {

    ThreeHundredSixtysixToSeventytyFive THSSTSF=null;

    @Before
    public void setup(){
        THSSTSF=new ThreeHundredSixtysixToSeventytyFive();
    }

    @Test
    public void testFindLeaves()throws Exception{
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        THSSTSF.findLeavesByHeight(root);
    }

    @Test
    public void testIsperfect(){
            System.out.println(808281+"  "+THSSTSF.isPerfectSquare(808201));
    }

    @Test
    public void testkSmallestPairs()throws Exception{
        int []nums1={1,1,2};
        int []nums2={1,2,3};
        System.out.println(THSSTSF.kSmallestPairs(nums1,nums2,9));
    }

    @Test
    public void testLargeSubset()throws Exception{
        int []nums={1,2,3};
        THSSTSF.largestDivisibleSubset(nums);
    }

}