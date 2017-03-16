package common;

/**
 * Created by tao on 3/16/17.
 */
public class DoubleListNode{
    public int val;
    public int count=0;
    public DoubleListNode pre;
    public DoubleListNode next;
    public DoubleListNode(int val){
        this.val=val;
        this.pre=null;
        this.count=0;
        this.next=null;
    }
}
