package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 4/2/17.
 */
public class ZigzagIterator {

    private Queue<Iterator<Integer>>q=null;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q=new LinkedList<>();
        if(!v1.isEmpty())
            q.offer(v1.iterator());
        if(!v2.isEmpty())
            q.offer(v2.iterator());
    }

    public int next() {
        Iterator<Integer>iterator=q.poll();
        int val=iterator.next();
        if(iterator.hasNext())
            q.offer(iterator);
        return val;
    }
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
