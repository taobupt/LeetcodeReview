package design;

import java.util.Stack;

/**
 * Created by tao on 3/16/17.
 */
public class MinStackOneStack{
    private Stack<Integer> stk=null;
    int min=Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStackOneStack() {
        stk=new Stack<>();
    }

    public void push(int x) {
        if(x<=min){
            stk.push(min);
            min=x;
        }
        stk.push(x);
    }

    public void pop() {
        if(stk.pop()==min)
            min=stk.pop();
    }

    public int top() {
        return stk.peek();
    }

    public int getMin() {
        return min;
    }
    public boolean isEmpty(){
        return stk.isEmpty();
    }
}
