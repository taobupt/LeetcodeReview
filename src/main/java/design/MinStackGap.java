package design;

import java.util.Stack;

/**
 * Created by tao on 3/16/17.
 */
public class MinStackGap {
    private Stack<Long>stk=null;
    long min=0;

    public MinStackGap(){
        stk=new Stack<>();
    }

    public void push(int x){
        if(stk.isEmpty()){
            stk.push(0L);
            min=x;
        }else{
            stk.push(x-min);
            if(x<min)
                min=x;
        }
    }

    public void pop(){
        if(stk.isEmpty())
            return;
        long top=stk.pop();
        if(top<0)
            min-=top;
    }


    public int top(){
        long top=stk.peek();

        if(top>0)
            return (int)(min+top);
        else
            return (int)min;//非常经典啊
    }
    public int getMin(){
        return (int)min;
    }
}
