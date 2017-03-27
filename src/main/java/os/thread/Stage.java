package os.thread;

/**
 * Created by tao on 3/26/17.
 */
public class Stage extends Thread {

    public void run(){
        ArmyRunable armyTaskOfSuiDynasty=new ArmyRunable();
        ArmyRunable armyTaskOfRevolt=new ArmyRunable();


        //使用runnable 借口创建线程
        Thread armyOfSuiDynasty=new Thread(armyTaskOfSuiDynasty,"隋军");
        Thread armyOfRevolt=new Thread(armyTaskOfRevolt,"农民军");

        armyOfSuiDynasty.start();
        armyOfRevolt.start();
        try{
            Thread.sleep(50);
            System.out.println("sleep 50 ms");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("双方激战正酣，半路杀出个程咬金");
        Thread mrCheng=new KeyPersonThread();
        mrCheng.setName("程咬金");
        System.out.println("his dream is to end the way");

        armyTaskOfSuiDynasty.keepFighting=false;
        armyTaskOfRevolt.keepFighting=false;

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        mrCheng.start();

        try{
            mrCheng.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("war has ended ");

    }

    public static void main(String []args){
        new Stage().start();
    }
}
