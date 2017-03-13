package contest;

import common.Trie;
import common.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tao on 3/12/17.
 */
public class LeetcodeContest {
//    //要是string很长，内存很容易贵。还真是扯淡
//    public List<String> wordsAbbreviation(List<String> dict) {
//        List<String>res=new ArrayList<>();
//        Map<String,List<String>>map=new HashMap<>();
//        int n=dict.size();
//        for(String str:dict){
//            int m=str.length();
//            if(str.length()<=3||String.valueOf(m-2).length()>=m-2){
//                map.put(str,new ArrayList<>());
//                map.get(str).add(str);
//            }
//
//            else{
//                StringBuilder sb=new StringBuilder();
//                sb.append(str.charAt(0)).append(String.valueOf(m-2)).append(str.charAt(m-1));
//                if(!map.containsKey(sb.toString()))
//                    map.put(sb.toString(),new ArrayList<>());
//                map.get(sb.toString()).add(str);
//
//            }
//
//        }
//
//        //find the duplicate
//        Map<String,String>map1=new HashMap<>();
//        for(Map.Entry<String,List<String>>entry:map.entrySet()){
//            List<String>li=entry.getValue();
//            if(li.size()==1)
//                continue;
//            Trie t=new Trie();
//            for(String word:li)
//                t.addWords(word);
//            int nn=li.size();
//            for(int i=0;i<nn;++i){
//                char []ss=li.get(i).toCharArray();
//                TrieNode root=t.root;
//                int ind=0;
//                while(root!=null && ind<ss.length && root.cnt!=1){
//                    root=root.children[ss[ind++]-'a'+26];
//                }
//                StringBuilder prefix=new StringBuilder();
//                if(ind<=ss.length-3){
//                    prefix.append(li.get(i).substring(0,ind));
//                    prefix.append((ss.length-prefix.length()-1)).append(ss[ss.length-1]);
//                }else
//                    prefix.append(ss);
//                if(prefix.length()<ss.length){
//                    map1.put(li.get(i),prefix.toString());
//                }else{
//                    map1.put(li.get(i),li.get(i));
//                }
//            }
//
//
//        }
//        for(Map.Entry<String,List<String>>entry:map.entrySet()){
//            if(entry.getValue().size()==1)
//                map1.put(entry.getValue().get(0),entry.getKey());
//        }
//        for(String str:dict)
//            res.add(map1.get(str));
//        return res;
//    }
//
    //brute force
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String>res=new ArrayList<>();
        Map<String,List<String>>map=new HashMap<>();
        int n=dict.size();
        for(String str:dict){
            int m=str.length();
            if(str.length()<=3||String.valueOf(m-2).length()>=m-2){
                map.put(str,new ArrayList<>());
                map.get(str).add(str);
            }
            else{
                StringBuilder sb=new StringBuilder();
                sb.append(str.charAt(0)).append(String.valueOf(m-2)).append(str.charAt(m-1));
                if(!map.containsKey(sb.toString()))
                    map.put(sb.toString(),new ArrayList<>());
                map.get(sb.toString()).add(str);

            }

        }

        //find the duplicate.solve the conflict
        Map<String,String>map1=new HashMap<>();
        for(Map.Entry<String,List<String>>entry:map.entrySet()){
            List<String>li=entry.getValue();
            if(li.size()==1)
                continue;
            int nn=li.size();
            for(int i=0;i<nn;++i){
                int k=li.get(i).length();
                String prefix="";
                int ind=1;
                while(ind<k){
                    prefix=li.get(i).substring(0,ind);
                    boolean flag=true;
                    for(int j=0;j<nn;++j){
                        if(i==j)
                            continue;
                        if(prefix.equals(li.get(j).substring(0,ind))){
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                        break;
                    ind++;
                }
                if(ind<=k-3){
                    map1.put(li.get(i),prefix+String.valueOf(k-prefix.length()-1)+li.get(i).charAt(k-1));
                }else
                    map1.put(li.get(i),li.get(i));

            }

        }
        for(Map.Entry<String,List<String>>entry:map.entrySet()){
            if(entry.getValue().size()==1)
                map1.put(entry.getValue().get(0),entry.getKey());
        }
        for(String str:dict)
            res.add(map1.get(str));
        return res;
    }
}
