package design;

import common.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 4/18/17.
 */

//其实就是一个insert interval的事情
public class SummaryRanges {

    private List<Interval>res=null;
    public SummaryRanges() {
        res=new ArrayList<>();
    }

    public void addNum(int val) {
        Interval newInterval=new Interval(val,val);
        int n=res.size(),i=0;
        List<Interval>ans=new ArrayList<>();
        while(i<n && res.get(i).end<newInterval.start-1){
            ans.add(res.get(i));
            i++;
        }
        while(i<n && newInterval.end>=res.get(i).start-1){
            newInterval.start=Math.min(newInterval.start,res.get(i).start);
            newInterval.end=Math.max(newInterval.end,res.get(i).end);
            i++;
        }
        ans.add(newInterval);
        while(i<n)
            ans.add(res.get(i++));
        res=ans;
    }


    public List<Interval> getIntervals() {
        return res;
    }
}
