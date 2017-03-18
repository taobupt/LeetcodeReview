package design;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tao on 3/18/17.
 */
public class TwoSumWithAdd {
    private Set<Integer>num=null;
    private Set<Integer>sum=null;

    public TwoSumWithAdd(){
        sum=new HashSet<>();
        num=new HashSet<>();
    }

    public void add(int x){
        if(num.contains(x)){
            sum.add(2*x);
        }else{
            Iterator<Integer>it=num.iterator();
            while(it.hasNext()){
                sum.add(it.next()+x);
            }
            num.add(x);
        }
    }

    public boolean find(int x){
        return sum.contains(x);
    }
}
