package os.thread;

/**
 * Created by tao on 3/26/17.
 */
public class ArmyRunable implements Runnable {

    //保证了线程可以正确的读取其他线程写入的值
    //不是可见行。 happens before.
    volatile  boolean keepFighting=true;

    public void run(){
        while(keepFighting){
            for(int i=0;i<5;++i)
                System.out.println(Thread.currentThread().getName()+"attack enemy ["+i+" ]");
            Thread.yield();//让出资源
        }

        System.out.printf(Thread.currentThread().getName()+"has finished");
    }
}
