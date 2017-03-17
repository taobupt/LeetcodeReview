package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/16/17.
 */
public class MinStackTest {


    @Test
    public void testMinstack(){
        MinStackOneStack stk=new MinStackOneStack();
        stk.push(1);
        stk.push(2);
        stk.push(3);
        stk.push(4);
        stk.push(-1);
        System.out.println(stk.top());
        stk.pop();
        stk.pop();
        System.out.println(stk.top());
        stk.pop();
        stk.pop();
        stk.pop();
        stk.pop();
    }

}