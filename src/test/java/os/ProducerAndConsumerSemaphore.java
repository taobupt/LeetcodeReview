package os;

/**
 * Created by tao on 3/18/17.
 */
import java.util.concurrent.Semaphore;
public class ProducerAndConsumerSemaphore {

    private int cnt;
    private Semaphore mutex=null;
    private Semaphore notFull=null;
    private Semaphore notEmpty=null;
    public ProducerAndConsumerSemaphore(){
        cnt=0;
        mutex=new Semaphore(1);
        notFull=new Semaphore(2);
        notEmpty=new Semaphore(0);
    }

    public void produce()throws InterruptedException{
        Thread.sleep(100);
        while (true){
            try{
                notFull.acquire();
                mutex.acquire();
                cnt++;
                System.out.println(Thread.currentThread().getName()+ "生产者生产，目前总共有" + cnt);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                mutex.release();
                notEmpty.release();
            }
        }
    }

    public void consume()throws InterruptedException {
        Thread.sleep(100);
        while (true){
            try {
                notEmpty.acquire();
                mutex.acquire();
                cnt--;
                System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + cnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notFull.release();
            }

        }
    }

}
