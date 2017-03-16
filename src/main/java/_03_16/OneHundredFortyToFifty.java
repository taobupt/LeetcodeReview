package _03_16;


import common.ListNode;
import common.Point;

import java.util.*;

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

    //143 reorder list
    public ListNode reverseList(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode newHead=null;
        while(head!=null){
            ListNode next=head.next;
            head.next=newHead;
            newHead=head;
            head=next;
        }
        return newHead;
    }
    public void reorderList(ListNode head) {
        if(head==null||head.next==null)
            return;
        ListNode fast=head.next;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        fast=slow.next;
        slow.next=null;
        slow=head;
        fast=reverseList(fast);
        while(fast!=null){
            ListNode node1=fast.next;
            ListNode node2=slow.next;
            fast.next=slow.next;
            slow.next=fast;
            fast=node1;
            slow=node2;
        }
    }

    //147 insertion sort list
    //For God's sake, don't try sorting a linked list during the interview
    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode first=new ListNode(Integer.MIN_VALUE);
        ListNode p=first;
        while(head!=null){
            p=first;
            ListNode tmp=head.next;
            while(p.next!=null && p.next.val<head.val){
                p=p.next;
            }
            head.next=p.next;
            p.next=head;
            head=tmp;
        }
        return first.next;
    }

    //148 merge sort list
    //space is O(logn)
    //you can try bottom to up
    public ListNode merge(ListNode l1,ListNode l2){
        ListNode first=new ListNode(0);
        ListNode p=first;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                p.next=l1;
                l1=l1.next;
            }else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }
        if(l1!=null)
            p.next=l1;
        if(l2!=null)
            p.next=l2;
        return first.next;
    }
    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode fast=head.next;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        fast=slow.next;
        slow.next=null;
        return merge(sortList(head),sortList(fast));
    }


    //149 max points in a line
    //第一种直接计算斜率
    //遇到高精度，就会跪了。
    public int maxPoints(Point[] points) {
        Map<Double,Integer>map=new HashMap<>();
        if(points==null)
            return 0;
        int maxValue=0,n=points.length;
        for(int i=0;i<n;++i){
            int same=1,vertival=0,value=0;
            map.clear();
            for(int j=0;j<n;++j){
                if(j==i)
                    continue;
                if(points[i].x==points[j].x && points[j].y==points[i].y)
                    same++;
                else if(points[i].x==points[j].x)
                    vertival++;
                else{
                    double slope=1.0*(points[j].y-points[i].y)/(points[j].x-points[i].x);
                    if(!map.containsKey(slope))
                        map.put(slope,1);
                    else
                        map.put(slope,map.get(slope)+1);
                    value=Math.max(map.get(slope),value);
                }
            }
            value=Math.max(value+same,vertival+same);
            maxValue=Math.max(maxValue,value);
        }
        return maxValue;
    }


    //应该用gcd来表示
    public int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    public int maxPointsGCD(Point[] points) {
        if(points==null)
            return 0;
        int maxValue=0,n=points.length;
        Map<Integer,Map<Integer,Integer>>map=new HashMap<>();
        for(int i=0;i<n;++i){
            map.clear();
            int same=1,predencular=0,cnt=0;
            for(int j=i+1;j<n;++j){
                if(points[i].x==points[j].x && points[i].y==points[j].y)
                    same++;
                else if(points[i].x==points[j].x)
                    predencular++;
                else{
                    int xx=(points[i].x-points[j].x);
                    int yy=(points[i].y-points[j].y);
                    int GCD=gcd(xx,yy);
                    xx/=GCD;
                    yy/=GCD;
                    if(!map.containsKey(xx)){
                        Map<Integer,Integer>map1=new HashMap<>();
                        map1.put(yy,1);
                        map.put(xx,map1);
                    }else{
                        if(!map.get(xx).containsKey(yy))
                            map.get(xx).put(yy,1);
                        else
                            map.get(xx).put(yy,map.get(xx).get(yy)+1);
                    }
                    cnt=Math.max(cnt,map.get(xx).get(yy));
                }
            }
            cnt=Math.max(cnt+same,same+predencular);
            maxValue=Math.max(maxValue,cnt);
        }
        return maxValue;
    }


    //150 evaluate reverse polish notation
    //use stack
    public int evalRPN(String[] tokens) {
        Stack<Integer>stk=new Stack<>();
        int n=tokens.length;
        for(int i=0;i<n;++i){
            if(tokens[i].equals("+")){
                int a=stk.pop();
                stk.push(a+stk.pop());
            }else if(tokens[i].equals("-")){
                int a=stk.pop();
                stk.push(stk.pop()-a);
            }else if(tokens[i].equals("*")){
                int a=stk.pop();
                stk.push(stk.pop()*a);
            }else if(tokens[i].equals("/")){
                int a=stk.pop();
                stk.push(stk.pop()/a);
            }else{
                stk.push(Integer.valueOf(tokens[i]));
            }
        }
        return stk.peek();
    }

    //想不到这道题还可以用递归来做
    //居然会快很多。果然是好解法
    public int eval(String[]tokens,List<String>li){
        int n=li.size();
        String str=li.get(n-1);
        li.remove(n-1);
        if(str.equals("+")||str.equals("-")||str.equals("/")||str.equals("*")){
            int r1=eval(tokens,li);
            int r2=eval(tokens,li);
            if(str.equals("+"))
                return r1+r2;
            else if(str.equals("-"))
                return r2-r1;
            else if(str.equals("*"))
                return r2*r1;
            else
                return r2/r1;
        }
        return Integer.valueOf(str);
    }
    public int evalRPNRecursive(String []tokens){
        int n=tokens.length;
        List<String>li=new ArrayList<>(Arrays.asList(tokens));
        return eval(tokens,li);
    }

    //Convert Expression to Reverse Polish Notation
    //an interesting
}
