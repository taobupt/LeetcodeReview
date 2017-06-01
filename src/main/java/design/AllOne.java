package design;

import common.Tuple;

import java.util.*;

/**
 * Created by tao on 5/31/17.
 */

//和LFU差不多。
    //自己实现双链表
public class AllOne {

    Map<String,Integer> map=null;
    TreeMap<Integer,List<String>>treeMap=null;
    Map<String,Integer>mapInd=null;
    public AllOne() {
        map=new HashMap<>();
        treeMap=new TreeMap<>();
        mapInd=new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(!map.containsKey(key)){
            map.put(key,1);
            if(!treeMap.containsKey(1))
                treeMap.put(1,new ArrayList<>());
            treeMap.get(1).add(key);
            mapInd.put(key,treeMap.get(1).size()-1);
        }
        else{
            int cnt=map.get(key);
            map.put(key,cnt+1);

            //remove from cnt;
            int size = treeMap.get(cnt).size();
            if(size!=1){
                treeMap.get(cnt).set(mapInd.get(key),treeMap.get(cnt).get(size-1));
                mapInd.put(treeMap.get(cnt).get(size-1),mapInd.get(key));
            }
            treeMap.get(cnt).remove(size-1);
            if(treeMap.get(cnt).isEmpty())
                treeMap.remove(cnt);

            //add to cnt+1
            if(!treeMap.containsKey(cnt+1))
                treeMap.put(cnt+1,new ArrayList<>());
            treeMap.get(cnt+1).add(key);
            mapInd.put(key,treeMap.get(cnt+1).size()-1);//update mapIndex;
        }
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(map.containsKey(key)){
            int cnt = map.get(key);
            if(map.get(key)==1){
                map.remove(key);
            }
            else{
                map.put(key,map.get(key)-1);
            }


            //remove from cnt;
            //add into cnt-1;
            int size = treeMap.get(cnt).size();
            if(size!=1){
                treeMap.get(cnt).set(mapInd.get(key),treeMap.get(cnt).get(size-1));
                mapInd.put(treeMap.get(cnt).get(size-1),mapInd.get(key));
            }
            treeMap.get(cnt).remove(size-1);
            if(treeMap.get(cnt).isEmpty())
                treeMap.remove(cnt);
            mapInd.remove(key);
            //add into cnt-1
            if(cnt>1){
                if(!treeMap.containsKey(cnt-1)){
                    treeMap.put(cnt-1,new ArrayList<>());
                    treeMap.get(cnt-1).add(key);
                    mapInd.put(key,0);
                }else{
                    treeMap.get(cnt-1).add(key);
                    mapInd.put(key,treeMap.get(cnt-1).size()-1);
                }
            }

        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(treeMap.isEmpty()||treeMap.lastEntry().getValue().isEmpty())
            return "";
        return treeMap.lastEntry().getValue().get(0);
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(treeMap.isEmpty()||treeMap.firstEntry().getValue().isEmpty())
            return "";
        return treeMap.firstEntry().getValue().get(0);
    }
}
