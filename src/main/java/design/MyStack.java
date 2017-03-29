package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tao on 3/28/17.
 */

//比较优秀的是用一个queue,每次入队列的时候，就把前n-1个元素pop出来，重新入队列。嗯嗯。
public class MyStack {
    private Queue<Integer>q1=null;
    private Queue<Integer>q2=null;

    /** Initialize your data structure here. */
    public MyStack() {
        q1=new LinkedList<>();
        q2=new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(!q1.isEmpty()){
            while(q1.size()!=1){
                q2.offer(q1.poll());
            }
            return q1.poll();
        }else{
            while(q2.size()!=1){
                q1.offer(q2.poll());
            }
            return q2.poll();
        }
    }

    /** Get the top element. */
    public int top() {
        if(!q1.isEmpty()){
            while(q1.size()!=1){
                q2.offer(q1.poll());
            }
            int top=q1.poll();
            q2.offer(top);
            return top;
        }else{
            while(q2.size()!=1){
                q1.offer(q2.poll());
            }
            int top=q2.poll();
            q1.offer(top);
            return top;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}

