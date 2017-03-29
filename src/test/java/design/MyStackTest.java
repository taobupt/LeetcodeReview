package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/28/17.
 */
public class MyStackTest {

    @Test
    public void testMystack(){
        MyStack stack=new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
        System.out.println(stack.pop());
        stack.push(4);
        while(!stack.empty()){
            System.out.println(stack.pop());
        }
    }

}