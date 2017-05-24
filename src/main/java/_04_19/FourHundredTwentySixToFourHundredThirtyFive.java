package _04_19;

import common.Interval;

/**
 * Created by tao on 5/23/17.
 */
public class FourHundredTwentySixToFourHundredThirtyFive {


    //432 All O`one Data Structure 不太会

    //434
    public int countSegments(String s) {
        String []args = s.split("\\s+");
        int cnt=0;
        for(String arg:args){
            if(arg.length()!=0)
                cnt++;
        }
        return cnt;
    }

    //space;
    public int countSegmentsWithSpace(String s){
        char []ss=s.toCharArray();
        int n=ss.length;
        int cnt=0,i=0;
        while(i<n){
            while(i<n && Character.isSpaceChar(ss[i]))
                i++;
            if(i<n)// 为什么放在这里呢？若是放下面的话，就会少算一个，为啥判断小于n呢，不判断就会出现corn case
                cnt++;
            while(i<n && !Character.isSpaceChar(ss[i]))
                i++;
        }
        return cnt;
    }

    // non-overlapping interval
    //没啥印象
    //先排序，发现有重叠就删之。
    public int eraseOverlapIntervals(Interval[] intervals) {
        return 0;
    }

}
