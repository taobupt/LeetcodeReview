package os.thread;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 3/26/17.
 */
public class ReaderAndWriterTest {

    ReaderAndWriter readerAndWriter=null;
    @Before
    public void setup(){
        readerAndWriter=new ReaderAndWriter();
    }

    @Test
    public void testReaderWriter()throws InterruptedException{
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    readerAndWriter.read();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    readerAndWriter.write();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    readerAndWriter.read();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread t4=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    readerAndWriter.read();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t3.start();
        t2.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

}