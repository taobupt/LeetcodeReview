package common;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tao on 5/25/17.
 */

//硬做还真是做出来了，卧槽，感觉如果都是o(1)的话，那得双联表了。
public class LFUCache {

    class Node{
        public int val;
        public int key;
        public Node next;
        public Node prev;
        public Node(int key,int val){
            this.val=val;
            next=null;
            prev=null;
            this.key=key;
        }
    }

    private Map<Integer,Tuple<Integer,Node>>map=null;//数据，次数 & node
    private TreeMap<Integer,Node> map1=null;//次数，链表头
    private int capacity=0;
    public LFUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        map1=new TreeMap<>();
    }

    public void adjust(int cnt,int key,int val){
        map.put(key,new Tuple(cnt+1,map.get(key).y));//增加一个
        map.get(key).y.val=val;
        if(map1.get(cnt).next==null){
            map1.remove(cnt);
        }else{

            if(map.get(key).y.prev==null){
                map1.put(cnt,map.get(key).y.next);
            }
            if( map.get(key).y.next!=null)
                map.get(key).y.next.prev=map.get(key).y.prev;
            if( map.get(key).y.prev!=null)
                map.get(key).y.prev.next=map.get(key).y.next;
            map.get(key).y.prev=null;
            map.get(key).y.next=null;
        }

        if(!map1.containsKey(cnt+1)){
            map1.put(cnt+1,map.get(key).y);
        }else{
            map.get(key).y.next=map1.get(cnt+1);
            map1.get(cnt+1).prev=map.get(key).y;
            map1.put(cnt+1,map.get(key).y);
        }
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        int cnt = map.get(key).x;
        int val = map.get(key).y.val;
        adjust(cnt,key,val);
        return val;
        //cnt 那个链表里剔除这个node
        //cnt+1 链表表头增加这个node；
    }

    public void put(int key, int value) {
        if(capacity==0)
            return;
        if(map.containsKey(key)){
            int cnt = map.get(key).x;
            adjust(cnt,key,value);
        }else{
            if(map.size()<capacity){
                map.put(key,new Tuple(1,new Node(key,value)));//增加一个
                if(!map1.containsKey(1)){
                    map1.put(1,map.get(key).y);
                }else{
                    //insert into head
                    map.get(key).y.next=map1.get(1);
                    map1.get(1).prev=map.get(key).y;
                    map1.put(1,map.get(key).y);
                }
            }else{
                //evict the data
                int cnt = map1.firstKey();
                int keyy = 0;
                if(map1.get(cnt).next==null){
                    keyy = map1.get(cnt).key;
                    map1.remove(cnt);
                }else{
                    Node head=map1.get(cnt);
                    while(head.next!=null)
                        head=head.next;
                    keyy =head.key;
                    head.prev.next=null;
                    head.prev=null;
                }
                map.remove(keyy);
                map.put(key,new Tuple(1,new Node(key,value)));//增加一个
                if(!map1.containsKey(1)){
                    map1.put(1,map.get(key).y);
                }else{
                    //insert into head
                    map.get(key).y.next=map1.get(1);
                    map1.get(1).prev=map.get(key).y;
                    map1.put(1,map.get(key).y);
                }

            }
        }
    }
}
