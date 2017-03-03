package _03_02;

import common.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tao on 3/2/2017.
 */
public class TwentyOneToThirtyTest {

    TwentyOneToThirty tt=null;

    @Before
    public void setup(){
        tt=new TwentyOneToThirty();
    }

    @Test
    public void testGenerate()throws Exception{
        tt.generateParenthesisByStack(3);
    }

    @Test
    public void testKGroup()throws Exception{
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(6);
        tt.reverseKGroupIterative(head,3);

    }

    @Test
    public void testDivide()throws Exception{
        System.out.println(tt.divide(-2147488,1));
    }

    @Test
    public void testfindSubstring()throws Exception{
        String s="barfoothefoobarman";
        String []words={"foo","bar"};
        tt.findSubstring(s,words);
    }

}