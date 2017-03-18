package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tao on 3/18/17.
 */


//trade off.
// 还有一种是看哪种操作更多的，如果是查询更多的话，那么提前准备好更好。
// 两种操作必有一种是o(1),o(n)
//一个list，一个map会tle，只能用一个map
public class TwoSum {

    private Map<Integer,Integer> map=null;
    /** Initialize your data structure here. */
    public TwoSum() {
        map=new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if(!map.containsKey(number))
            map.put(number,1);
        else
            map.put(number,map.get(number)+1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Map.Entry<Integer,Integer>entry:map.entrySet()){
            int x=entry.getKey();
            int y=value-x;
            if((x==y && map.get(x)>=2)||(x!=y && map.containsKey(y)))
                return true;
        }
        return false;
    }
}