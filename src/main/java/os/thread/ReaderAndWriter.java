package os.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by tao on 3/26/17.
 */
public class ReaderAndWriter {
    private Semaphore mutex=new Semaphore(2);//at most 10 reader
    private Semaphore wrt=new Semaphore(1);//mutex between reader and writer
    private int readcnt=0;
    public void read()throws InterruptedException{
        Thread.sleep(3000);
        while(true){
            mutex.acquire();
            readcnt++;
            System.out.println("have "+readcnt+" readers");
            if(readcnt==1)
                wrt.acquire();
            mutex.release();

            //want to leave
            mutex.acquire();
            readcnt--;
            System.out.println("reader is leaving"+" has "+readcnt+"reader");
            if(readcnt==0)
                wrt.release();
            mutex.release();

        }
    }

    public void write()throws InterruptedException{
        Thread.sleep(3000);
        while (true){
            wrt.acquire();
            System.out.println("writer is writing");
            wrt.release();
        }
    }
}
