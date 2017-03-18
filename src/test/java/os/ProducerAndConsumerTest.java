package os;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/17/17.
 */

/*
The main thread creates and starts the t1 and t2 threads. The two threads start running in parallel.
The main thread calls t1.join() to wait for the t1 thread to finish.
The t1 thread completes and the t1.join() method returns in the main thread.
The main thread calls t2.join() to wait for the t2 thread to finish.
The t2 thread completes (or completed before the t1 thread did) and the t2.join() method returns in the main thread.
 */
public class ProducerAndConsumerTest {

    ProducerAndConsumer pc=null;
    @Test
    public void testPC()throws Exception{
        pc=new ProducerAndConsumer();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    pc.produce();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    pc.consume();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}