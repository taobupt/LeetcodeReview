package _04_23;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredSixtySixToFourHundredSeventyFive {




    // 467. Unique Substrings in Wraparound String
    //都是dp，都不会啊，稍微难一点的dp就跪了，真是个忧伤的故事
    public int findSubstringInWraproundString(String p) {
        return 0;
    }


    //474 ones and zeros
    public int findMaxForm(String[] strs, int m, int n) {
        int k=strs.length;
        int [][]dp=new int[m+1][n+1];//m is zero and n is 1
        for(int i=0;i<k;++i){
            char []ss=strs[i].toCharArray();
            int ones = 0,length=ss.length;
            for(char c:ss){
                if(c=='1')
                    ones++;
            }
            for(int v1=m;v1>=length-ones;--v1){
                for(int v2=n;v2>=ones;--v2){

                    dp[v1][v2]=Math.max(dp[v1][v2],dp[v1-length+ones][v2-ones]+1);

                }
            }
        }
        return dp[m][n];
    }

     // 475 heaters, 我记得还是挺难的,wrong answer
     public int findRadius(int[] houses, int[] heaters) {
        int m=houses.length,n=heaters.length;
         Arrays.sort(houses);
         Arrays.sort(heaters);
         //相差不大，唉，就是没想到，我这种方法好歹也过了一半的测试样栗子
//        int res=0,i=0,j=0;
//        while(i<m && j<n-1){
//            res=Math.max(res,Math.min(Math.abs(houses[i]-heaters[j]),Math.abs(houses[i]-heaters[j+1])));
//            i++;
//            if(i<m &&houses[i]>heaters[j+1])
//                j++;
//        }
//        while(i<m){
//            res=Math.max(res,Math.abs(houses[i]-heaters[n-1]));
//            i++;
//        }
//        return res;
         //总而言之就是找到一个合适的heater来帮助取暖
         int res=Integer.MIN_VALUE;
         int j=0;
         for(int i=0;i<houses.length;++i){
             while(j<heaters.length-1 && Math.abs(heaters[j]-houses[i])>=Math.abs(heaters[j+1]-houses[i]))//少了个等于就坏事了
                 j++;
             res=Math.max(res,Math.abs(heaters[j]-houses[i]));
         }
         return res;
     }
}
