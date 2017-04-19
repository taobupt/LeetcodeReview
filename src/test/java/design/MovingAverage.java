package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tao on 4/18/17.
 */

//还是可以用window 那个做的
public class MovingAverage {

    private Queue<Integer> q=null;
    private int capacity;
    private long sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        q=new LinkedList<>();
        this.capacity=size;
        sum=0;
    }

    public double next(int val) {
//        if(q.size()<capacity){
//            q.offer(val);
//            sum+=val;
//            return sum*1.0/q.size();
//        }else{
//            int top=q.poll();
//            sum-=top;
//            sum+=val;
//            q.offer(val);
//            return sum*1.0/capacity;
//        }

        //concise, you can also use the array
        if(q.size() == capacity){
            sum = sum - q.poll();
        }
        q.offer(val);
        sum += val;
        return sum/q.size();
    }
}
