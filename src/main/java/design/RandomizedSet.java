package design;

import java.util.*;

/**
 * Created by tao on 5/13/17.
 */
public class RandomizedSet {

    private Map<Integer,Integer>map=null;
    private ArrayList<Integer>nums=null;
    private Random random =null;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map=new HashMap<>();
        nums=new ArrayList<>();
        random =new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        map.put(val,nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    //only remove index
    //swap to last element to delete
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int index  = map.get(val);
        if(index!=nums.size()-1){
            int lastValue = nums.get(nums.size()-1);
            //转移这个到前面的index
            nums.set(index,lastValue);
            map.put(lastValue,index);
        }
        nums.remove(nums.size()-1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
