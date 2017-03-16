package design;

/**
 * Created by tao on 3/16/17.
 */
import common.DoubleLinkedList;
import common.DoubleListNode;
import common.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tao on 3/16/17.
 */
//思路就是用hashmap存要删除的index，这样就会比较快
public class LRUCache {

    //key, value, and front node
    //如果是双链表的话，会更方便一点。
    private Map<Integer, Tuple<Integer, DoubleListNode>>map=null;
    private DoubleLinkedList dl=null;
    private int capacity=0;
    public LRUCache(int capacity) {
        map=new HashMap<>();
        dl=new DoubleLinkedList();
        this.capacity=capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        dl.remove(map.get(key).y);//move the key in front of the list
        int val=map.get(key).x;
        dl.insertFromHead(map.get(key).y);

        map.put(key,new Tuple(val,dl.head.next));
        return val;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(dl.size==capacity){
                //delete
                map.remove(dl.tail.pre.val);
                dl.remove(dl.tail.pre);
            }
            //insert
            DoubleListNode node=new DoubleListNode(key);
            dl.insertFromHead(node);
            map.put(key,new Tuple(value,dl.head.next));
        }else{
            dl.remove(map.get(key).y);
            dl.insertFromHead(map.get(key).y);
            map.put(key,new Tuple(value,dl.head.next));
        }
    }
}

