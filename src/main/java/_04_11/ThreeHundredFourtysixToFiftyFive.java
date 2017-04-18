package _04_11;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao on 4/17/17.
 */
public class ThreeHundredFourtysixToFiftyFive {

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
}
