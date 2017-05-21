package design;

import java.util.*;

/**
 * Created by tao on 5/13/17.
 */
//上一题的翻版// 也是可以用 LinkedHashSet<Integer>,这样就可以不管indexof了，有时间得做一做
public class RandomizedCollection {

    /** Initialize your data structure here. */
    private Random random =null;
    private Map<Integer,List<Integer>>map=null;
    private ArrayList<Integer> nums =null;
    public RandomizedCollection() {
        nums=new ArrayList();
        map=new HashMap<>();
        random=new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            map.get(val).add(nums.size());
            nums.add(val);
            return false;
        }
        map.put(val,new ArrayList<>());
        map.get(val).add(nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        //swap val to the last position
        int lastIndex = nums.size()-1;
        if(map.get(val).contains(lastIndex)){
            nums.remove(lastIndex);
            if(map.get(val).size()==1){
                map.remove(val);
            }else{
                int index = map.get(val).indexOf(lastIndex);
                map.get(val).remove(index);
            }
        }else{
            //swap last element into valid position
            int index = map.get(val).size()-1;
            nums.set(map.get(val).get(index),nums.get(lastIndex));
            map.get(nums.get(lastIndex)).add(map.get(val).get(index));
            if(map.get(val).size()==1){
                map.remove(val);
            }else{
                map.get(val).remove(index);
            }

            //delete the last element;
            index = map.get(nums.get(lastIndex)).indexOf(lastIndex);
            map.get(nums.get(lastIndex)).remove(index);
            nums.remove(lastIndex);

        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
