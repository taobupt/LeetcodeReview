package design;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tao on 5/13/17.
 */
public class PhoneDirectory {



    private Set<Integer> set=null;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set=new HashSet<>();
        for(int i=0;i<maxNumbers;++i)
            set.add(i);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        Iterator<Integer>iterator = set.iterator();
        if(iterator!=null && iterator.hasNext()){
            int val = iterator.next();
            set.remove(val);
            return val;
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
