package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tao on 3/28/17.
 */
public class WordDistance {

    private Map<String,List<Integer>> map=null;
    public WordDistance(String[] words) {
        map=new HashMap<>();
        int n=words.length;
        for(int i=0;i<n;++i){
            if(!map.containsKey(words[i]))
                map.put(words[i],new ArrayList<>());
            map.get(words[i]).add(i);
        }
    }

    //非常重要的思路；
    public int shortest(String word1, String word2) {
        List<Integer>index1=map.get(word1);
        List<Integer>index2=map.get(word2);
        int m=index1.size(),n=index2.size();
        int i=0,j=0;
        int minValue=Integer.MAX_VALUE;
        while(i<m && j<n){
            minValue=Math.min(minValue,Math.abs(index1.get(i)-index2.get(j)));
            if(index1.get(i)<index2.get(j))
                i++;
            else
                j++;
        }
        return minValue;
    }
}
