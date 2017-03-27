package os.thread;

/**
 * Created by tao on 3/26/17.
 */
public class KeyPersonThread extends Thread {


    public void run(){
        System.out.println(getName()+"starts fighting");
        for(int i=0;i<10;++i)
            System.out.println(getName()+"左右屠杀");
        System.out.println(getName()+"has finish fighting");
    }
}
