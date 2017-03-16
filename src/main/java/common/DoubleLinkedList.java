package common;

/**
 * Created by tao on 3/16/17.
 */

public class DoubleLinkedList {
    public int size;
    public  DoubleListNode head=null;
    public DoubleListNode tail=null;
    public DoubleLinkedList(){
        this.size=0;
        head=new DoubleListNode(0);
        tail=new DoubleListNode(0);
        head.next=tail;
        tail.pre=head;
    }

    public void remove(DoubleListNode node){
        if(node!=null){
            node.pre.next=node.next;
            node.next.pre=node.pre;
            this.size--;
        }
    }
    public void insertFromHead(DoubleListNode node){
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;
        this.size++;
    }
    public void insertFromTail(DoubleListNode node){
        tail.pre.next=node;
        node.pre=tail.pre;
        node.next=tail;
        tail.pre=node;
        this.size++;
    }
    public void insert(DoubleListNode node){

    }
}
