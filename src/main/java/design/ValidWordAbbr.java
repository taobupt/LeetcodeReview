package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tao on 4/15/2017.
 */
public class ValidWordAbbr {
    private Map<String,Integer> map=null;
    private Map<String,Integer>set=null;
    public ValidWordAbbr(String[] dictionary) {
        map=new HashMap<>();
        set=new HashMap<>();
        for(String str:dictionary){
            if(!set.containsKey(str))
                set.put(str,1);
            else
                set.put(str,set.get(str)+1);
            if(str.length()<=2){
                int val=map.getOrDefault(str,0);
                map.put(str,val+1);
            }
            else{
                int n=str.length();
                StringBuilder sb=new StringBuilder();
                sb.append(str.charAt(0)).append(n-2).append(str.charAt(n-1));
                int val=map.getOrDefault(sb.toString(),0);
                map.put(sb.toString(),val+1);
                //set.add(str.charAt(0)+(n-2)+str.charAt(n-1)+"");
            }
        }
    }

    public boolean isUnique(String word) {
        if(word.length()<=2)
            return !map.containsKey(word)||set.get(word)==map.get(word);
        else{
            int n=word.length();
            StringBuilder sb=new StringBuilder();
            sb.append(word.charAt(0)).append(n-2).append(word.charAt(n-1));
            return !map.containsKey(sb.toString())||set.get(word)==map.get(sb.toString());
        }
    }
}
