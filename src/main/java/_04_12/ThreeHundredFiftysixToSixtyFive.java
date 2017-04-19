package _04_12;

import design.NestedInteger;

import java.util.List;

/**
 * Created by tao on 4/19/17.
 */
public class ThreeHundredFiftysixToSixtyFive {

    //364 Nested List Weight Sum II
    //dfs 或者是bfs，每次都保留值。
    //居然错了一次，我曹
    public int getDepth(List<NestedInteger> nestedList){
        int n=nestedList.size();
        int ans=0;
        for(int i=0;i<n;++i){
            if(!nestedList.get(i).isInteger()){
                //depth[0]++;
                ans=Math.max(ans,1+getDepth(nestedList.get(i).getList()));
            }else{
                ans=Math.max(ans,1);
            }
        }
        return ans;
    }

    public void dfs(List<NestedInteger> nestedList,int[]sum,int level){
        int n=nestedList.size();
        for(int i=0;i<n;++i){
            if(!nestedList.get(i).isInteger()){
                dfs(nestedList.get(i).getList(),sum,level-1);
            }else{
                sum[0]+=level*nestedList.get(i).getInteger();
            }
        }
    }
    public int depthSumInverse(List<NestedInteger> nestedList) {
        //int []depth=new int[1];
        int height=getDepth(nestedList);
        int []sum=new int[1];
        dfs(nestedList,sum,height);
        return sum[0];
    }

}
