package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tao on 4/19/17.
 */
//应该在gethits那里去掉的
public class HitCounter {

    private int hits;
    private Queue<Integer>q=null;
    /** Initialize your data structure here. */
    public HitCounter() {
        hits=0;
        q=new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits++;
        q.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!q.isEmpty() && q.peek()<=timestamp-300){
            q.poll();
            hits--;
        }
        return hits;
    }
}
