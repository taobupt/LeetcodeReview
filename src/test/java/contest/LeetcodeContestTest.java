package contest;

import common.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/12/17.
 */
public class LeetcodeContestTest {

    LeetcodeContest lc=null;

    @Before
    public void setup(){
        lc=new LeetcodeContest();
    }
    @Test
    public void wordsAbbreviation() throws Exception {
        String []strs={"bbbbab","bbbaab","baaaab"};
        List<String> dict=new ArrayList<>(Arrays.asList(strs));
        List<String>res=lc.wordsAbbreviation(dict);
        for(String str:res)
            System.out.println(str);
    }
    @Test
    public void test(){
        TreeNode root=new TreeNode(2);
        root.left=new TreeNode(1);
        root.right=new TreeNode(3);
        TreeNode node=lc.convertBST(root);
        System.out.println("hello");
    }

    @Test
    public void test1(){
        List<List<Integer>>matrix=new ArrayList<>();
        List<Integer>res1=new ArrayList<>();
        res1.add(1);
        res1.add(1);
        res1.add(1);
        res1.add(1);
        matrix.add(res1);
        List<Integer>res2=new ArrayList<>();
        res2.add(0);
        res2.add(1);
        res2.add(1);
        res2.add(1);
        matrix.add(res2);
        List<Integer>res3=new ArrayList<>();
        res3.add(1);
        res3.add(1);
        res3.add(1);
        res3.add(1);
        matrix.add(res3);
        lc.updateMatrix(matrix);

    }

    @Test
    public void test2(){
//        lc.checkPerfectNumber(1);
//        System.out.println(lc.complexNumberMultiply("1++1i","1++1i"));
//        TreeNode node=new TreeNode(20);
//        node.left=new TreeNode(8);
//        node.right=new TreeNode(22);
//        node.right.right=new TreeNode(25);
//        node.left.left=new TreeNode(4);
//        node.left.right=new TreeNode(12);
//        node.left.right.left=new TreeNode(10);
//        node.left.right.right=new TreeNode(14);
//        lc.boundaryOfBinaryTree(node);
        String[]args={"aaa","aa","aaa"};
        System.out.println(lc.findLUSlengthII(args));
        int [][]M={{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(lc.findCircleNum(M));
    }

}