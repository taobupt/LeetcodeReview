package design;

import java.util.Stack;

/**
 * Created by tao on 3/16/17.
 */

//俩stack 没啥意思，应该用一个stack，来存gap
//其实存tuple也可以减少到一个栈，但总感觉不太对。我记得是是存gap的。
public class MinStack {

    /** initialize your data structure here. */
    private Stack<Integer> s1=null;
    private Stack<Integer>mins=null;//min stack
    public MinStack() {
        s1=new Stack<>();
        mins=new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
        if(mins.isEmpty()||mins.peek()>x)
            mins.push(x);
        else
            mins.push(mins.peek());
    }

    public void pop() {
        s1.pop();
        mins.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return mins.peek();
    }
}



