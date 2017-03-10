package common;

/**
 * Created by tao on 3/10/17.
 */
public class List {
    private ListNode dummy=null;
    public List(){
        dummy=new ListNode(0);
    }

    public ListNode createList(int []nums){
        ListNode p=dummy;
        for(int x:nums){
            p.next=new ListNode(x);
            p=p.next;
        }
        return dummy.next;
    }

    public void printList(ListNode node){
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
}
