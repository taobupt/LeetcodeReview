package os;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tao on 3/17/17.
 */
public class ProducerAndConsumer {
    private LinkedList<Integer> list=null;
    private int capacity=0;
    public ProducerAndConsumer(){
        this.capacity=2;
        list=new LinkedList<>();
    }

    public void produce()throws InterruptedException{
        int value=0;
        while(true){
            synchronized (this){
                while(list.size()==capacity)
                    wait();
                System.out.println("producer produced -"+value);

                //to insert the jobs in the list
                list.add(value++);

                //notifies the consumer that not it can start consuming
                notify();

                //makes the working of program easier to understand
                Thread.sleep(10);

            }
        }
    }

    public void consume()throws InterruptedException{
        while(true){
            synchronized (this){
                while(list.isEmpty())
                    wait();


                //to retrive the first jon in the list;
                int val=list.removeFirst();
                System.out.println("Consumer consumed-"+val);
                notify();

                Thread.sleep(10);
            }
        }
    }
}
