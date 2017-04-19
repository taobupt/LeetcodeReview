package _04_11;

import common.Tuple;

import java.util.*;

/**
 * Created by tao on 4/17/17.
 */
public class ThreeHundredFourtysixToFiftyFive {


    //346 Moving Average from Data Stream
    //in design


    //347 Top k frequent elements
    //可以想想用bucket sort如何做
    //简单来说，就是声明一个nums.legnth+1的list数组，然后插进去，bucket的长度为1
    //Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
    public List<Integer> topKFrequent(int[] nums, int k) {
        //优先级队列了
        Map<Integer,Integer> map=new HashMap<>();
        for(int x:nums){
            if(!map.containsKey(x))
                map.put(x,1);
            else
                map.put(x,1+map.get(x));
        }
        List<Tuple<Integer,Integer>>Elements=new ArrayList<>();
        for(Map.Entry<Integer,Integer>entry:map.entrySet()){
            Elements.add(new Tuple(entry.getKey(),entry.getValue()));
        }

        //做小堆就是这个做的。
        PriorityQueue<Tuple<Integer,Integer>>pq=new PriorityQueue<>(new Comparator<Tuple<Integer, Integer>>() {
            @Override
            public int compare(Tuple<Integer, Integer> o1, Tuple<Integer, Integer> o2) {
                if(o1.y==o2.y)
                    return 0;
                else if(o1.y>o2.y)
                    return 1;
                else
                    return -1;
            }
        });
        for(Tuple<Integer,Integer>tuple:Elements){
            if(pq.size()<k)
                pq.offer(tuple);
            else{
                if(pq.peek().y<tuple.y){
                    pq.poll();
                    pq.offer(tuple);
                }
            }
        }
        List<Integer>res=new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(pq.poll().x);
        }
        return res;
    }

    //348 in design design Tic-Tac-Toe

    //349. Intersection of Two Arrays
    //sort+binary search+two pointers
    //hash map
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length)
            return intersection(nums2,nums1);
        //int m=nums1.length,n=nums2.length;
        Set<Integer> set=new HashSet<>();
        Set<Integer>res=new HashSet<>();
        for(int x:nums1)
            set.add(x);
        for(int x:nums2)
            if(set.contains(x))
                res.add(x);
        int []ans=new int[res.size()];
        int index=0;
        for(int x:res)
            ans[index++]=x;
        return ans;
    }

    //350 Intersection of Two ArraysII
    //排序，俩指针才是王道啊
    /*
    If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory,
    and record the intersections.

If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort),
then read 2 elements from each array at a time in memory, record intersections.
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        //俩hashmap 搞定
        if(nums1.length>nums2.length)
            return intersect(nums2,nums1);
        Map<Integer,Integer>map1=new HashMap<>();
        Map<Integer,Integer>map2=new HashMap<>();
        for(int x:nums1)
            map1.put(x,map1.getOrDefault(x,0)+1);
        for(int y:nums2)
            map2.put(y,map2.getOrDefault(y,0)+1);
        List<Integer>res=new ArrayList<>();
        for(Map.Entry<Integer,Integer>entry:map1.entrySet()){
            int key=entry.getKey();
            if(map2.containsKey(key)){
                int cnt=Math.min(entry.getValue(),map2.get(key));
                for(int i=0;i<cnt;++i)
                    res.add(key);
            }
        }
        int n=res.size();
        int []ans=new int[n];
        for(int i=0;i<n;++i)
            ans[i]=res.get(i);
        return ans;

    }

    //352 data stream as disjoint intervals
    //其实就是insert interval, 其实还是有些不同
    //in design

    //354 russian doll envelop
    //效率太低，如何用binary search，我也是醉了
    //都是dp+binary search的，这个要好好利用一下
    public int maxEnvelopes(int[][] envelopes) {
        //the same as the long increasing path
        int n=envelopes.length;
        int ans=0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        int []dp=new int[n+1];
        for(int i=1;i<=n;++i){
            dp[i]=1;
            for(int j=1;j<i;++j){
                if(envelopes[j-1][0]<envelopes[i-1][0] && envelopes[j-1][1]<envelopes[i-1][1]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }
}
