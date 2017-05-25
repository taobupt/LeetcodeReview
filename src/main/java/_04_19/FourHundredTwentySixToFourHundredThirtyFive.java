package _04_19;

import common.Interval;

import java.util.Arrays;
import java.util.Comparator;

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
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start!=o2.start)
                    return o1.start-o2.start;
                else
                    return o1.end-o2.end;
            }
        });
        int cnt=0,n=intervals.length;
        if(n<=1)
            return 0;
        int end=intervals[0].end;
        for(int i=1;i<n;++i){
            if(intervals[i].start<end){
                cnt++;
                end=Math.min(end,intervals[i].end);//漏掉了，唉
                continue;
            }else{
                end=Math.max(end,intervals[i].end);
            }
        }
        return cnt;
    }

}
