package _03_09;

import common.ListNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/9/17.
 */
public class EightyOneToNinetyTest {
    EightyOneToNinety en=null;


    @Before
    public void setup(){
        en=new EightyOneToNinety();
    }

    @Test
    public void partition() throws Exception {
        ListNode head=new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(2);
        head.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next=new ListNode(2);
        en.partition(head,3);
    }

    @Test
    public void graycode()throws  Exception{
        en.grayCode(2);
    }

}