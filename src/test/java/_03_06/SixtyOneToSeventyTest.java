package _03_06;

import common.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/6/17.
 */
public class SixtyOneToSeventyTest {

    SixtyOneToSeventy ss=null;

    @Before
    public void setup(){
        ss=new SixtyOneToSeventy();
    }
    @Test
    public void rotateRight() throws Exception {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        ss.rotateRight(head,2);
    }

    @Test
    public void testSqrt()throws Exception{
        for(int i=1;i<=100;++i) {
            System.out.println(i + "  " + ss.mySqrtNewton(i));
        }
    }
    @Test
    public void testaddBinary()throws Exception{
        System.out.println(ss.addBinaryRecur("1111","10101010"));
    }

}