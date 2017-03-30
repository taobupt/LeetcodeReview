package design;

import java.util.Stack;

/**
 * Created by tao on 3/28/17.
 */

//two stacks ,要是用一个stack的话，其实，每次插入都应该把它插到底部
public class MyQueue {
    private Stack<Integer>stk1=null;
    private Stack<Integer>stk2=null;

    /** Initialize your data structure here. */
    public MyQueue() {
        stk1=new Stack<>();
        stk2=new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stk1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stk2.isEmpty()){
            while(!stk1.isEmpty()){
                stk2.push(stk1.pop());
            }
        }
        return stk2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(stk2.isEmpty()){
            while(!stk1.isEmpty()){
                stk2.push(stk1.pop());
            }
        }
        return stk2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stk1.isEmpty() && stk2.isEmpty();
    }
}