package design;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tao on 4/5/17.
 */

//没啥好说的，就是两个q，能做到logn
public class MedianFinder {

    private PriorityQueue<Integer>q1=null;
    private PriorityQueue<Integer>q2=null;

    /** initialize your data structure here. */
    public MedianFinder() {
        q1=new PriorityQueue<>();//顶部最小，但是装的都是大的
        q2=new PriorityQueue<>(Collections.reverseOrder());
    }

    //还是很重要的，这一点，复习复习
    public void addNum(int num) {
        if(q1.isEmpty()||num>q1.peek())
            q1.offer(num);
        else
            q2.offer(num);
        while(q2.size()>q1.size())
            q1.offer(q2.poll());
        while(q1.size()>q2.size()+1){
            q2.offer(q1.poll());
        }
    }

    public double findMedian() {
        int m=q1.size();
        int n=q2.size();
        return (m+n)%2!=0?1.0*q1.peek():(q1.peek()+q2.peek())/2.0;
    }
}