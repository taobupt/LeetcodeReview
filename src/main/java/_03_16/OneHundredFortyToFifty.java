package _03_16;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao on 3/16/17.
 */
public class OneHundredFortyToFifty {

    //141 linked list cycle
    //naive way use set
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
            return false;
        Set<ListNode> set=new HashSet<>();
        ListNode p=head;
        while(p!=null && !set.contains(p)){
            set.add(p);
            p=p.next;
        }
        return p!=null;
    }

    //fast and slow pointer
    public boolean hasCycleConcise(ListNode head){
        if(head==null||head.next==null)
            return false;
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        return slow==fast;
    }

    //change the structure of the list
    //tag nodes that has been visited
    public boolean hasCycle1(ListNode head){
        if(head == null || head.next == null) return false;
        if(head.next == head) return true;
        ListNode nextNode = head.next;
        head.next = head;//tag nodes that has been visited
        boolean isCycle = hasCycle1(nextNode);
        return isCycle;
    }

    //142 linked list cycleII
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null)
            return null;
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow)
                break;
        }
        if(fast!=slow)
            return null;
        fast=head;
        while(fast!=slow){
            slow=slow.next;
            fast=fast.next;
        }
        return fast;
    }

    //也是可用用set
    public ListNode detectCycleSet(ListNode head){
        if(head==null||head.next==null)
            return null;
        Set<ListNode>set=new HashSet<>();
        ListNode p=head;
        while(p!=null && !set.contains(p)){
            set.add(p);
            p=p.next;
        }
        return p;
    }
}
