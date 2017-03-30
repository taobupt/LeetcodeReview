package design;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/28/17.
 */
public class MyQueueTest {
    @Test
    public void testMyqueue(){
        MyQueue mq=new MyQueue();
        mq.push(1);
        mq.push(2);
        mq.push(3);
        System.out.println(mq.peek());
        System.out.println(mq.pop());
        while(!mq.empty()){
            System.out.println(mq.pop());
        }
    }

}